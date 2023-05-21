package com.panorbit.test.dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.panorbit.test.model.DetailedResponse;
import com.panorbit.test.util.Queries;

@Component
public class DashboardDao {

	public Optional<CountriesDetails<List<Country>>> getMatchingCountriesByname(String searchParam, Connection conn) {
		String GET_MATCHING_COUNTRIES_BY_NAME = "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 FROM country WHERE Name like '%"
				+ searchParam + "%'";
		try (PreparedStatement ps = conn.prepareStatement(GET_MATCHING_COUNTRIES_BY_NAME)) {
			try (ResultSet rs = ps.executeQuery()) {
				List<Country> countries = new ArrayList<>();
				while (rs.next()) {
					countries.add(new Country(rs.getString("Code"), rs.getString("Name"), rs.getString("Continent"),
							rs.getString("Region"), rs.getFloat("SurfaceArea"), rs.getInt("IndepYear"),
							rs.getInt("Population"), rs.getFloat("LifeExpectancy"), rs.getFloat("GNP"),
							rs.getFloat("GNPOld"), rs.getString("LocalName"), rs.getString("GovernmentForm"),
							rs.getString("HeadOfState"), rs.getInt("Capital"), rs.getString("Code2")));
				}
				return countries.size() > 0
						? Optional.of(new CountriesDetails<List<Country>>(countries.size(), countries))
						: Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<CitiesDetails<List<City>>> getMatchingCititesByname(String searchParam, Connection conn) {
		String GET_MATCHING_CITIES_BY_NAME = "SELECT ID, Name, CountryCode, District, Population FROM city WHERE Name like '%"
				+ searchParam + "%'";
		try (PreparedStatement ps = conn.prepareStatement(GET_MATCHING_CITIES_BY_NAME)) {
			try (ResultSet rs = ps.executeQuery()) {
				List<City> cities = new ArrayList<>();
				while (rs.next()) {
					cities.add(new City(rs.getInt("ID"), rs.getString("Name"), rs.getString("CountryCode"),
							rs.getString("District"), rs.getInt("Population")));
				}
				return cities.size() > 0 ? Optional.of(new CitiesDetails<List<City>>(cities.size(), cities))
						: Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<LanguageDetails<List<LanguageCountry>>> getMatchingLanguagesByname(String searchParam,
			Connection conn) {
		String GET_MATCHING_LANGUAGES_BY_NAME = "SELECT CountryCode, Language, IsOfficial, Percentage FROM countrylanguage WHERE Language like '%"
				+ searchParam + "%'";
		try (PreparedStatement ps = conn.prepareStatement(GET_MATCHING_LANGUAGES_BY_NAME)) {
			try (ResultSet rs = ps.executeQuery()) {
				List<LanguageCountry> languages = new ArrayList<>();
				while (rs.next()) {
					languages.add(new LanguageCountry(rs.getString("CountryCode"), rs.getString("Language"),
							rs.getString("IsOfficial"), rs.getFloat("Percentage")));
				}
				return languages.size() > 0
						? Optional.of(new LanguageDetails<List<LanguageCountry>>(languages.size(), languages))
						: Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<DetailedResponse<Country, CitiesDetails<List<City>>, LanguageDetails<List<LanguageCountry>>>> getCountryByCountryCode(
			String countryCode, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(Queries.GET_COUNTRY_BY_CODE)) {
			ps.setString(1, countryCode);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Country country = new Country(rs.getString("Code"), rs.getString("Name"), rs.getString("Continent"),
							rs.getString("Region"), rs.getFloat("SurfaceArea"), rs.getInt("IndepYear"),
							rs.getInt("Population"), rs.getFloat("LifeExpectancy"), rs.getFloat("GNP"),
							rs.getFloat("GNPOld"), rs.getString("LocalName"), rs.getString("GovernmentForm"),
							rs.getString("HeadOfState"), rs.getInt("Capital"), rs.getString("Code2"));
					Optional<CitiesDetails<List<City>>> optionalCityByCountryCode = getCityByCountryCode(countryCode, conn);
					Optional<LanguageDetails<List<LanguageCountry>>> optionalLanguageByCountryCode = getLanguageCountryCode(countryCode, conn);
					return Optional.of(new DetailedResponse<>(country, optionalCityByCountryCode.orElseGet(null), optionalLanguageByCountryCode.orElseGet(null)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<CitiesDetails<List<City>>> getCityByCountryCode(String countryCode, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(Queries.GET_CITIES_BY_COUNTRY_CODE)) {
			ps.setString(1, countryCode);
			try (ResultSet rs = ps.executeQuery()) {
				List<City> cities = new ArrayList<>();
				while (rs.next()) {
					cities.add(new City(rs.getInt("ID"), rs.getString("Name"), rs.getString("CountryCode"),
							rs.getString("District"), rs.getInt("Population")));
				}
				return cities.size() > 0 ? Optional.of(new CitiesDetails<>(cities.size(), cities)) : Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<LanguageDetails<List<LanguageCountry>>> getLanguageCountryCode(String countryCode, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(Queries.GET_LANGUAGES_BY_COUNTRY_CODE)) {
			ps.setString(1, countryCode);
			try (ResultSet rs = ps.executeQuery()) {
				List<LanguageCountry> languages = new ArrayList<>();
				while (rs.next()) {
					languages.add(new LanguageCountry(rs.getString("CountryCode"), rs.getString("Language"),
							rs.getString("IsOfficial"), rs.getFloat("Percentage")));
				}
				return languages.size() > 0 ? Optional.of(new LanguageDetails<>(languages.size(), languages)) : Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

}
