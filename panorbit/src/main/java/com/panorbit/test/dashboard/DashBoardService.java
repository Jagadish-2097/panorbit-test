package com.panorbit.test.dashboard;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panorbit.test.model.DataHolder;
import com.panorbit.test.model.DetailedResponse;

@Service
public class DashBoardService {

	@Autowired
	private DashboardDao dashboardDao;

	public List<DataHolder> getListOfMatchingRecords(String searchParam, Connection conn) {
		Optional<CountriesDetails<List<Country>>> optionalCountriesDetails = dashboardDao
				.getMatchingCountriesByname(searchParam, conn);
		Optional<CitiesDetails<List<City>>> optionalCitiesDetails = dashboardDao.getMatchingCititesByname(searchParam,
				conn);
		Optional<LanguageDetails<List<LanguageCountry>>> optionalLanguageDetails = dashboardDao
				.getMatchingLanguagesByname(searchParam, conn);
		List<DataHolder> data = new ArrayList<>();
		data.addAll(getCountriesMap(optionalCountriesDetails));
		data.addAll(getCitiesMap(optionalCitiesDetails));
		data.addAll(getLanguagesMap(optionalLanguageDetails));
		return data;
	}

	private List<DataHolder> getCountriesMap(Optional<CountriesDetails<List<Country>>> optionalCountriesDetails) {
		return optionalCountriesDetails.isPresent() ? optionalCountriesDetails.get().getCountryDetails().stream()
				.map(country -> new DataHolder("Country", country.getCode(), country.getName()))
				.collect(Collectors.toList()) : new ArrayList<>();

	}

	private List<DataHolder> getCitiesMap(Optional<CitiesDetails<List<City>>> optionalCitiesDetails) {
		return optionalCitiesDetails.isPresent() ? optionalCitiesDetails.get().getCityDetails().stream()
				.map(cities -> new DataHolder("City", cities.getCountryCode(), cities.getName()))
				.collect(Collectors.toList()) : new ArrayList<>();

	}

	private List<DataHolder> getLanguagesMap(Optional<LanguageDetails<List<LanguageCountry>>> optionalLanguageDetails) {
		return optionalLanguageDetails.isPresent()
				? optionalLanguageDetails.get().getLanguageDetails().stream()
						.map(language -> new DataHolder("language", language.getCountryCode(), language.getLanguage()))
						.collect(Collectors.toList())
				: new ArrayList<>();

	}

	public Optional<DetailedResponse<CountriesDetails<List<Country>>, CitiesDetails<List<City>>, LanguageDetails<List<LanguageCountry>>>> getDetailedListOfSearchedData(String searchParam,
			Connection conn) {
		Optional<CountriesDetails<List<Country>>> optionalCountriesDetails = dashboardDao
				.getMatchingCountriesByname(searchParam, conn);
		Optional<CitiesDetails<List<City>>> optionalCitiesDetails = dashboardDao.getMatchingCititesByname(searchParam,
				conn);
		Optional<LanguageDetails<List<LanguageCountry>>> optionalLanguageDetails = dashboardDao
				.getMatchingLanguagesByname(searchParam, conn);
		return Optional.of(new DetailedResponse<>(optionalCountriesDetails.orElse(null), optionalCitiesDetails.orElse(null), optionalLanguageDetails.orElse(null)));
	}

	public Optional<DetailedResponse<Country, CitiesDetails<List<City>>, LanguageDetails<List<LanguageCountry>>>> getDetailsByCountryCode(String countryCode, Connection conn) {
		return dashboardDao.getCountryByCountryCode(countryCode, conn);
	}
}
