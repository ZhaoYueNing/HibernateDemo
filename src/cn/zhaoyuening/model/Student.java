package cn.zhaoyuening.model;

/**
 * Created by buynow on 16-10-1.
 */
public class Student {
    private Integer id;
    private String name;
    private Classes classes;

    public Student (){}

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student (String name){
        this.name = name;
    }
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

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classes=" + classes +
                '}';
    }
}
