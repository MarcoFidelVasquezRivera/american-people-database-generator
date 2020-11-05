package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Database {
	public static final int NUMBER_OF_DIGITS = 13;
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
		
	}

	public void generatePeople(long nPeople) throws IOException {
		ArrayList<String> names = new ArrayList<>();
		ArrayList<String> lastNames = new ArrayList<>();
		Random random = new Random();
		
		names = loadData("data/babynames-clean.csv");
		lastNames = names = loadData("data/Names_2010Census.csv");
		
		for(long i=0;i<nPeople;i++) {
			String name = names.get(random.nextInt(names.size()));//generates the firstName
			name = name+" "+names.get(random.nextInt(names.size()));//generates the second name
			
			String lastName = lastNames.get(random.nextInt(lastNames.size()));//generates the firstName
			lastName = lastName+" "+lastNames.get(random.nextInt(lastNames.size()));//generates the second name
			
			generatePerson(name,lastName);
		}


		
	}
	
	public ArrayList<String> loadData(String path) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(path)); 
		ArrayList<String> data = new ArrayList<>();
		
		String line = br.readLine();
		while(line!=null) {
			line = capitalize(line);
			data.add(line.split(",")[0]);
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
	
	public void generatePerson(String name,String lastName) throws IOException {
		long code = generateCode();
		String gender = generateGender();
		int age = generateAge();
		
		System.out.println(name+" "+lastName+" "+code+" "+gender+" "+age);

		
	
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
	
	public Date generateBirthdate() {
		Calendar cl = new GregorianCalendar();
		
		
		
		return null;
	}
	
	public int generateAge() throws IOException {
		ArrayList<String> agespercent = loadData("data/ages-percents.txt");
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
			//System.out.println(minValue+" "+maxValue+" "+number+" "+percent);
			
			if(number<percent) {
				age = random.nextInt(maxValue-minValue)+minValue;
				ageSetted = true;
			}
			
			counter++;
		}
		
		return age;
	}
	
	public String generateNationality() {
		return "";
	}
	
	
}
