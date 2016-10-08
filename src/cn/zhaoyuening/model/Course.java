package cn.zhaoyuening.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by buynow on 16-10-4.
 */
public class Course {
    private String name;
    private Set<Stu> stus = new HashSet<>();

    public Course(String name) {
        this.name = name;
    }

    public Course() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Stu> getStus() {
        return stus;
    }

    public void setStus(Set<Stu> stus) {
        this.stus = stus;
    }

    public void addStu(Stu stu) {
        stus.add(stu);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}
