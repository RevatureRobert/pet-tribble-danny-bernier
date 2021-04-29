package dev.tribble.model;

public class Lab {
    private int labId;
    private String name;

    public Lab() {}

    public Lab(int labId, String name) {
        this.labId = labId;
        this.name = name;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Lab{" +
                "labId=" + labId +
                ", name='" + name + '\'' +
                '}';
    }
}
