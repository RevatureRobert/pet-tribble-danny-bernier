package dev.tribble.model;

public class Tribble {

    private int id;
    private String name;
    private int age;
    private int labId;

    public Tribble(){}

    public Tribble(int id, String name, int age, int labId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.labId = labId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    @Override
    public String toString() {
        return "Tribble{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", labId=" + labId +
                '}';
    }
}
