package com.panorbit.test.dashboard;

public class City {
	private Integer ID;
	private String Name;
	private String CountryCode;
	private String District;
	private Integer Population;

	public City(Integer iD, String name, String countryCode, String district, Integer population) {
		ID = iD;
		Name = name;
		CountryCode = countryCode;
		District = district;
		Population = population;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public Integer getPopulation() {
		return Population;
	}

	public void setPopulation(Integer population) {
		Population = population;
	}

}
