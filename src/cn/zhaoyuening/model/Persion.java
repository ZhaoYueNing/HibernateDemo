package cn.zhaoyuening.model;

public class Persion {
	private Integer id;
	private String name;
	private Integer age;
	private Character gender;
	
	public Persion(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Persion [id=" + id + ", name=" + name + ", age=" + age
				+ ", gender=" + gender + "]";
	}
	
	
}
