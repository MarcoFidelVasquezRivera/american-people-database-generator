
package model;

public class Person {

	private int code;
	private int age;
	private String name;
	private String lastName;
	private String gender;
	private String date;
	private int height;
	private String nationality;
	private String photography;

	public Person(int code, int age, String name, String lastName, String gender, String date, int height,
			String nationality) {
		this.code = code;
		this.age = age;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.date = date;
		this.height = height;
		this.nationality = nationality;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhotography() {
		return photography;
	}

	public void setPhotography(String photography) {
		this.photography = photography;
	}

	public String toString() {
		return name + "   " + lastName + "  " + gender + "    " + date + "    " + height + "      " + nationality;
		
	}
}
