package com.panorbit.test.dashboard;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panorbit.test.dbconfig.ConnectionPool;
import com.panorbit.test.exceptionhandler.NoDataFoundException;
import com.panorbit.test.model.DataHolder;
import com.panorbit.test.model.DetailedResponse;
import com.panorbit.test.model.ErrorResponse;
import com.panorbit.test.model.GenericResponse;
import com.panorbit.test.model.GenericResponseModel;
import com.panorbit.test.model.ResponseModel;

@RestController
public class DashBoardController {
	
	private ConnectionPool connectionPool;
	private DashBoardService dashBoardService;
	
	@Autowired
	public DashBoardController(ConnectionPool connectionPool, DashBoardService dashBoardService) {
		this.connectionPool = connectionPool;
		this.dashBoardService = dashBoardService;
	}
	
	@GetMapping("/search")
	public GenericResponse<GenericResponseModel<ResponseModel<Integer, List<DataHolder>>, ErrorResponse>> searchByCountryCityLanguage(String searchParam) throws SQLException {
		try (Connection conn = connectionPool.getConnection()) {
			return new GenericResponse<>(new GenericResponseModel<>(new ResponseModel<>(dashBoardService.getListOfMatchingRecords(searchParam, conn).size(), dashBoardService.getListOfMatchingRecords(searchParam, conn)), null), HttpStatus.OK);
		}
	}
	
	@GetMapping("/detailed-data")
	public GenericResponse<GenericResponseModel<DetailedResponse<CountriesDetails<List<Country>>, CitiesDetails<List<City>>, LanguageDetails<List<LanguageCountry>>>, Object>> getDetailedData(String searchParam) throws SQLException {
		try (Connection conn = connectionPool.getConnection()) {
			Optional<DetailedResponse<CountriesDetails<List<Country>>, CitiesDetails<List<City>>, LanguageDetails<List<LanguageCountry>>>> optionalDetailedResponse = dashBoardService.getDetailedListOfSearchedData(searchParam, conn);
			if (optionalDetailedResponse.isPresent()) return new GenericResponse<>(new GenericResponseModel<>(optionalDetailedResponse.get(), null), HttpStatus.OK); 
			throw new NoDataFoundException("No Data Found");
		}
	}
	
	@GetMapping("/country")
	public GenericResponse<GenericResponseModel<DetailedResponse<Country, CitiesDetails<List<City>>, LanguageDetails<List<LanguageCountry>>>, ErrorResponse>> getCountryDetails(String countryCode) throws SQLException {
		try (Connection conn = connectionPool.getConnection()) {
			Optional<DetailedResponse<Country, CitiesDetails<List<City>>, LanguageDetails<List<LanguageCountry>>>> optionalCountryDetails = dashBoardService.getDetailsByCountryCode(countryCode, conn);
			if (optionalCountryDetails.isPresent()) {
				return new GenericResponse<>(new GenericResponseModel<>(optionalCountryDetails.get(), null), HttpStatus.OK);
			}
		}
		throw new NoDataFoundException("Invalid Country Code");
	}
	
}
