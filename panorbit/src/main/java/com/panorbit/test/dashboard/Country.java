package com.panorbit.test.dashboard;

public class Country {
	private String Code;
	private String Name;
	private String Continent;
	private String Region;
	private Float SurfaceArea;
	private Integer IndepYear;
	private Integer Population;
	private Float LifeExpectancy;
	private Float GNP;
	private Float GNPOld;
	private String LocalName;
	private String GovernmentForm;
	private String HeadOfState;
	private Integer Capital;
	private String Code2;

	public Country(String code, String name, String continent, String region, Float surfaceArea, Integer indepYear,
			Integer population, Float lifeExpectancy, Float gNP, Float gNPOld, String localName, String governmentForm,
			String headOfState, Integer capital, String code2) {
		Code = code;
		Name = name;
		Continent = continent;
		Region = region;
		SurfaceArea = surfaceArea;
		IndepYear = indepYear;
		Population = population;
		LifeExpectancy = lifeExpectancy;
		GNP = gNP;
		GNPOld = gNPOld;
		LocalName = localName;
		GovernmentForm = governmentForm;
		HeadOfState = headOfState;
		Capital = capital;
		Code2 = code2;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getContinent() {
		return Continent;
	}

	public void setContinent(String continent) {
		Continent = continent;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public Float getSurfaceArea() {
		return SurfaceArea;
	}

	public void setSurfaceArea(Float surfaceArea) {
		SurfaceArea = surfaceArea;
	}

	public Integer getIndepYear() {
		return IndepYear;
	}

	public void setIndepYear(Integer indepYear) {
		IndepYear = indepYear;
	}

	public Integer getPopulation() {
		return Population;
	}

	public void setPopulation(Integer population) {
		Population = population;
	}

	public Float getLifeExpectancy() {
		return LifeExpectancy;
	}

	public void setLifeExpectancy(Float lifeExpectancy) {
		LifeExpectancy = lifeExpectancy;
	}

	public Float getGNP() {
		return GNP;
	}

	public void setGNP(Float gNP) {
		GNP = gNP;
	}

	public Float getGNPOld() {
		return GNPOld;
	}

	public void setGNPOld(Float gNPOld) {
		GNPOld = gNPOld;
	}

	public String getLocalName() {
		return LocalName;
	}

	public void setLocalName(String localName) {
		LocalName = localName;
	}

	public String getGovernmentForm() {
		return GovernmentForm;
	}

	public void setGovernmentForm(String governmentForm) {
		GovernmentForm = governmentForm;
	}

	public String getHeadOfState() {
		return HeadOfState;
	}

	public void setHeadOfState(String headOfState) {
		HeadOfState = headOfState;
	}

	public Integer getCapital() {
		return Capital;
	}

	public void setCapital(Integer capital) {
		Capital = capital;
	}

	public String getCode2() {
		return Code2;
	}

	public void setCode2(String code2) {
		Code2 = code2;
	}

}
