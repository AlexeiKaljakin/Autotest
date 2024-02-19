package data.fielddata.genderdata;

public enum GenderData {
    MALE("m"),
    FEMALE("f");

    private String name;

    private GenderData(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
