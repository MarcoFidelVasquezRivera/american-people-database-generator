package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import customExceptions.ElementAlreadyExistException;

public class Database {
	public static final int NUMBER_OF_DIGITS = 13;
	private AVLTree<String, Person> treeName;
	private AVLTree<String, Person> treeLastName;
	private AVLTree<String, Person> treeCompleteName;
	private RedBlackTree<Long, Person> treeCode;
	
	public Database() {
		treeName = new AVLTree<>();
		treeLastName = new AVLTree<>();
		treeCompleteName = new AVLTree<>();
		treeCode = new RedBlackTree<Long,Person>((long)-1,null);
	}

	public ArrayList<String> loadData(String path, int linesToSkip) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(path)); 
		ArrayList<String> data = new ArrayList<>();
		String line = "";
		
		for(int i=0;i<linesToSkip;i++) {
			line = br.readLine();
		}
		
		line = br.readLine();
		while(line!=null) {
			line = capitalize(line);
			data.add(line.split(",")[0]);
			line = br.readLine();
		}
		
		return data;
	}
	
	public ArrayList<String> loadCompleteData(String path, int linesToSkip) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(path)); 
		ArrayList<String> data = new ArrayList<>();
		String line = "";
		
		for(int i=0;i<linesToSkip;i++) {
			line = br.readLine();
		}
		
		line = br.readLine();
		while(line!=null) {
			String[] splitLine = line.split(",");
			line = capitalize(splitLine[0]+","+splitLine[1]);
			data.add(line);
			line = br.readLine();
		}
		
		return data;
	}
	
	public String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }
	    str = str.toLowerCase();
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	//datasetNames, datasetLastNames, datasetHeights, datasetCountries, datasetAges
	public void generatePerson(ArrayList<String> dataNames,ArrayList<String> dataLastNames,ArrayList<String> dataHeights,ArrayList<String> dataCountries,ArrayList<String> dataAges) throws IOException, ElementAlreadyExistException {
		String name = generateName(dataNames);
		String lastName = generateLastName(dataLastNames);
		long code = generateCode();
		String gender = generateGender();
		int age = generateAge(dataAges);
		int height = generateHeigth(age,dataHeights);
		String birthdate = generateBirthdate(age);
		String nationality = generateNationality(dataCountries);
		String completeName = name+" "+lastName;
		
		Person person = new Person(code, age, name, lastName, gender, birthdate, height, nationality);
		
		treeCode.redBlackInsertion(code, person);
		treeName.insert(person, name);
		treeLastName.insert(person, lastName);
		treeCompleteName.insert(person, completeName);
	}
	
	public void addPerson(String name, String lastName, long code, String gender, int age, int height,String date,String nationality,String completeName) throws ElementAlreadyExistException {
		Person person = new Person(code, age, name, lastName, gender, date, height, nationality);
		
		treeCode.redBlackInsertion(code, person);
		treeName.insert(person, name);
		treeLastName.insert(person, lastName);
		treeCompleteName.insert(person, completeName);
	}
	
	public String generateName(ArrayList<String> names) {
		Random random = new Random();
		
		String name = names.get(random.nextInt(names.size()));//generates the firstName
		name = name+" "+names.get(random.nextInt(names.size()));//generates the second name
		
		return name;
	}
	
	public String generateLastName(ArrayList<String> lastNames) {
		Random random = new Random();
		
		String lastName = lastNames.get(random.nextInt(lastNames.size()));//generates the firstName
		lastName = lastName+" "+lastNames.get(random.nextInt(lastNames.size()));//generates the second name
		
		return lastName;
	}
	
	public String generateGender() {
		Random random = new Random();
		boolean gender = random.nextBoolean();
		String genderString;
		
		if(gender) {
			genderString = "male";
		}else {
			genderString = "female";
		}
		return genderString;
	}
	
	public long generateCode() {
		Random random = new Random();
		String codeString = "";
		
		for(int i=0;i<NUMBER_OF_DIGITS;i++) {
			if(i==0) {
				codeString+= random.nextInt(9)+1;
			}else {
				codeString+= random.nextInt(10);
			}
		}
		
		return Long.parseLong(codeString);
	}
	
	public String generateBirthdate(int age) {
		Random random = new Random();
		LocalDate ld = LocalDate.now();
		int year = ld.getYear()-age;
		int month = random.nextInt(ld.getMonthValue())+1;
		int day = random.nextInt(29)+1;
		
		String birthdate = String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
		
		return birthdate;
	}
	
	public int generateAge(ArrayList<String> agespercent) throws IOException {
		//ArrayList<String> agespercent = loadData("data/ages-percents.txt");
		Random random = new Random();
		int number = random.nextInt(100)+1;
		boolean ageSetted = false;
		int age = 0;
		int minValue = 0;
		int maxValue = 0;
		int counter = 0;
		double percent = 0.0;
		while(!ageSetted) {
			String[] line = agespercent.get(counter).split("-");
			minValue = Integer.parseInt(line[0]);
			maxValue = Integer.parseInt(line[1]);
			percent += Double.parseDouble(line[2]);
			
			if(number<=percent) {
				age = random.nextInt(maxValue-minValue)+minValue;
				ageSetted = true;
			}
			
			counter++;
		}
		
		return age;
	}
	
	public int generateHeigth(int age,ArrayList<String> heightDistribution) throws IOException {
		//ArrayList<String> heightDistribution = loadData("data/heights.txt");
		Random random = new Random();
		boolean heightSetted = false;
		int counter = 0;
		int height = 0;
		
		while(!heightSetted) {
			String[] line = heightDistribution.get(counter).split("-");
			int maxAge = Integer.parseInt(line[0]);
			int minValue = Integer.parseInt(line[1]);
			int maxValue = Integer.parseInt(line[2]);
			
			if(age<=maxAge) {
				height = random.nextInt(maxValue-minValue)+minValue;
				heightSetted = true;
			}
			counter++;
		}
		return height;
	}
	
	public ArrayList<String> filterData(ArrayList<String> data,ArrayList<String> filter){
		ArrayList<String> filteredData = new ArrayList<>();
		
		for(int i=0;i<data.size();i++) {
			String dataLine = data.get(i);
			String country = dataLine.split(",")[0];
			for(int j=0;j<filter.size();j++) {
				String filterLine = filter.get(j);
				
				if(country.equalsIgnoreCase(filterLine)) {
					filteredData.add(dataLine);
				}
			}
			
		}
		
		return filteredData;
	}
	
	
	public String generateNationality(ArrayList<String> dataCountries) {
		Random random = new Random();
		double number = (double) random.nextInt(100)+1;
		boolean nationalitySetted = false;
		String nationality = "";
		int counter = 0;
		double percent = 0.0;
		
		while(!nationalitySetted) {
			String[] line = dataCountries.get(counter).split(",");
			String country = line[0];
			percent += Double.parseDouble(line[2]);
			//System.out.println(number+" "+percent+" "+country);
			
			if(number<=percent) {
				nationality = country;
				nationalitySetted = true;
			}
			counter++;
		}
		
		return nationality;
	}
	
	public ArrayList<String> generatePercentages(ArrayList<String> countries){
		ArrayList<String> newCountries = new ArrayList<String>();
		double population = 0;
		
		for(int i=0;i<countries.size();i++) {
			String countryPopulation = countries.get(i).split(",")[1];
			population += Double.parseDouble(countryPopulation); 
		}

		for(int i=0;i<countries.size();i++) {
			String countryPopulation = countries.get(i).split(",")[1];
			double countryPopulationPercent = Double.parseDouble(countryPopulation)/population;
			countryPopulationPercent = countryPopulationPercent*100.0;
			//System.out.println(countryPopulationPercent);
			newCountries.add(countries.get(i)+","+countryPopulationPercent);
			
		}
		return newCountries;
	}
	
	public void delete(long code, String name,String lastName) {
		treeCode.delete(code);
		treeName.delete(name);
		treeLastName.delete(lastName);
		treeCompleteName.delete(name+" "+lastName);
	}
	
	public Person search(String key, int mode) {
		Person person = null;
		
		switch(mode) {
		case 1:
			person = treeCode.searchValue(Long.parseLong(key)).getElement();
			break;
			
		case 2:
			person = treeName.searchValue(key).getElement();
			break;
			
		case 3:
			person = treeLastName.searchValue(key).getElement();
			break;
			
		case 4:
			person = treeCompleteName.searchValue(key).getElement();
			break;
		}
		
		
		return person;
	}

	public ArrayList<Person> searchList(String kay,int mode){
		ArrayList<Person> person = new ArrayList<>();
		switch(mode) {
		case 1:
			person = treeCode.searchList(Long.parseLong(kay));
			break;
			
		case 2:
			person = treeName.searchList(kay);
			break;
			
		case 3:
			person = treeLastName.searchList(kay);
			break;
			
		case 4:
			person = treeCompleteName.searchList(kay);
			break;
		}
		
		return person;
	}
	
}
