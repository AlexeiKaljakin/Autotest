package data.countrycities;

public enum CountryData {
    RUSSIA("Россия");

    private String nameCountry;

    private CountryData(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getNameCountry() {
        return this.nameCountry;
    }
}
