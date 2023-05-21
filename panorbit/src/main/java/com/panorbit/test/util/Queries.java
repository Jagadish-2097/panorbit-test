package com.panorbit.test.util;

public interface Queries {
	public String INSERT_NEW_USER = "INSERT INTO user (FirstName, LastName, Gender, Email, PhoneNumber) values (?,?,?,?,?)";
	public String GET_USER_BY_EMAIL = "SELECT FirstName, LastName, Gender, Email, PhoneNumber FROM user WHERE Email = ?";
	public String GET_MATCHING_COUNTRIES_BY_NAME = "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 FROM country WHERE Name like '%?%'";
	public String GET_COUNTRY_BY_CODE = "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 FROM country WHERE Code = ?";
	public String GET_CITIES_BY_COUNTRY_CODE = "SELECT s.ID, s.Name, s.CountryCode, s.District, s.Population FROM city s JOIN country c ON c.Code = s.CountryCode WHERE s.CountryCode = ?";
	public String GET_LANGUAGES_BY_COUNTRY_CODE = "SELECT CountryCode, Language, IsOfficial, Percentage FROM countrylanguage l JOIN country c ON c.Code = l.CountryCode WHERE l.CountryCode = ?";
}
