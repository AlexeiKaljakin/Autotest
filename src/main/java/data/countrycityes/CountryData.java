package data.countrycityes;

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
