package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Database {
	public static final int NUMBER_OF_DIGITS = 13;
	private int quantityOfPeople;
	/*	a randomly generated code +
	 * 	a name +
	 * 	last name +
	 * 	gender +
	 *  birthdate 
	 *  age +
	 *  height +
	 *  nationality 
	 *  and a picture and save it to the database.
	 */
	
	public Database() {
		quantityOfPeople = 0;
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
	public void generatePerson(ArrayList<String> dataNames,ArrayList<String> dataLastNames,ArrayList<String> dataHeights,ArrayList<String> dataCountries,ArrayList<String> dataAges) throws IOException {
		String name = generateName(dataNames);
		String lastName = generateLastName(dataLastNames);
		long code = generateCode();
		String gender = generateGender();
		int age = generateAge(dataAges);
		int height = generateHeigth(age,dataHeights);
		String birthdate = generateBirthdate(age);
		String nationality = generateNationality(dataCountries);
		
		System.out.println("name: "+name+"\n"+
							"lastName: "+lastName+"\n"+
							"code: "+code+"\n"+
							"gender: "+name+"\n"+
							"age: "+gender+"\n"+
							"height: "+height+"\n"+
							"birthdate: "+birthdate+"\n"+
							"nationality: "+nationality+"\n");
		
		System.out.println(name+" "+lastName+" "+code+" "+gender+" "+age+" "+height);

		
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
		Calendar calendar = Calendar.getInstance();
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
			String dataLine = data.get(i).split(",")[0];
			
			for(int j=0;j<filter.size();i++) {
				String filterLine = filter.get(j);
				
				if(dataLine.equalsIgnoreCase(filterLine)) {
					filteredData.add(dataLine);
				}
			}
			
		}
		
		return filteredData;
	}
	
	
	public String generateNationality(ArrayList<String> dataCountries) {
		//ArrayList<String> agespercent = loadData("data/ages-percents.txt");
		Random random = new Random();
		int number = random.nextInt(100)+1;
		boolean nationalitySetted = false;
		String nationality = "";
		int counter = 0;
		double percent = 0.0;
		
		while(!nationalitySetted) {
			String[] line = dataCountries.get(counter).split(",");
			String country = line[0];
			percent += Double.parseDouble(line[2]);
			System.out.println(number+" "+percent+" "+country);
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
			double countryPopulationPërcent = Double.parseDouble(countryPopulation)/population;
			countryPopulationPërcent = countryPopulationPërcent*100.0;
			newCountries.add(countries.get(i)+","+countryPopulation);
			
		}
		
		return newCountries;
	}
	
}
