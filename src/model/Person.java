
package model;

public class Person {

	private long code;
	private int age;
	private String name;
	private String lastName;
	private String gender;
	private String date;
	private double height;
	private String nationality;
	private String photography;

	public Person(long code, int age, String name, String lastName, String gender, String date, double height,
			String nationality) {
		this.code = code;
		this.age = age;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.date = date;
		this.height = height;
		this.nationality = nationality;
		this.photography = photography;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
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

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
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

}
