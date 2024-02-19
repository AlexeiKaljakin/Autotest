package data.fielddata;

public enum InputFieldData {
    FNAME("fname"),
    FNAMELATIN("fname_latin"),
    LNAME("lname"),
    LNAMELATIN("lname_latin"),
    BLOGNAME("blog_name"),
    DATEOFBRTH("date_of_birth"),
    COMPANY("company"),
    POSITION("work"),
    SKYPE("skype"),
    HABR("habr");

    private String name;

    private InputFieldData(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
