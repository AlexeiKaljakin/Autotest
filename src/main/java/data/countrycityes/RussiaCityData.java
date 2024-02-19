package data.countrycityes;

public enum RussiaCityData implements ICityData {
    MOSCOW("Москва", CountryData.RUSSIA);

    private String nameCity;
    private CountryData countryData;

    private RussiaCityData(String nameCity, CountryData countryData) {
        this.nameCity = nameCity;
        this.countryData = countryData;
    }

    public String getName() {
        return this.nameCity;
    }

    public CountryData getCountryData() {
        return this.countryData;
    }
}