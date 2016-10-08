package cn.zhaoyuening.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by buynow on 16-10-4.
 */
public class Stu {
    private Integer id;
    private String name;
    private Set<Course> courses = new HashSet<>();

    public Stu(String name) {
        this.name = name;
    }

    public Stu( ) {}

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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public String toString() {
        return "Stu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
