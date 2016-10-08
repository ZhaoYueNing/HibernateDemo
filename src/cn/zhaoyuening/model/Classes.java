package cn.zhaoyuening.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by buynow on 16-10-1.
 *
 */
public class Classes {
    private String name;
    private Set<Student> students = new HashSet<>();

    public Classes(){}
    public Classes(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    @Override
    public String toString() {
        return "Classes{" +
                "name='" + name + '\'' +
                '}';
    }
}
