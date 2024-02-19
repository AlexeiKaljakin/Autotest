package data.language;

public enum EnglishLevelData {
    FIRSTLEVEL("Начальный уровень (Beginner)");

    private String nameEnglishLevel;

    private EnglishLevelData(String nameEnglishLevel) {
        this.nameEnglishLevel = nameEnglishLevel;
    }

    public String getEnglishLevel() {
        return this.nameEnglishLevel;
    }
}
