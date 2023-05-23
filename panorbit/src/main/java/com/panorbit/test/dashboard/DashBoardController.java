package com.panorbit.test.dashboard;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.panorbit.test.dbconfig.ConnectionPool;
import com.panorbit.test.exceptionhandler.NoDataFoundException;
import com.panorbit.test.exceptionhandler.UnauthorizedException;
import com.panorbit.test.model.DataHolder;
import com.panorbit.test.model.DetailedResponse;
import com.panorbit.test.model.ErrorResponse;
import com.panorbit.test.model.GenericResponse;
import com.panorbit.test.model.GenericResponseModel;
import com.panorbit.test.model.ResponseModel;
import com.panorbit.test.user.UserController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false", allowedHeaders = "*")
@Validated
public class DashBoardController {

	private ConnectionPool connectionPool;
	private DashBoardService dashBoardService;

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	public DashBoardController(ConnectionPool connectionPool, DashBoardService dashBoardService) {
		this.connectionPool = connectionPool;
		this.dashBoardService = dashBoardService;
	}

	/***
	 * Search api takes @param searchParam and search operation is done for country,
	 * city or languages If records are matched for given searchParam, then matched
	 * records will be returned with status code 200, if not then empty json will be
	 * returned
	 */
	@GetMapping("/search/{session}")
	public GenericResponse<GenericResponseModel<ResponseModel<Integer, List<DataHolder>>, ErrorResponse>> searchByCountryCityLanguage(
			@PathVariable String session, String searchParam) throws SQLException {
		logger.info("session is " + session);
		if (dashBoardService.validateSession(session)) {
			
			try (Connection conn = connectionPool.getConnection()) {
				return new GenericResponse<>(
						new GenericResponseModel<>(
								new ResponseModel<>(dashBoardService.getListOfMatchingRecords(searchParam, conn).size(),
										dashBoardService.getListOfMatchingRecords(searchParam, conn)),
								null),
						HttpStatus.OK);
			}
		} else {
			throw new UnauthorizedException("Invalid session found");
		}

	}

	/***
	 * Once user searches for specific key or data then the same will be sent to
	 * the @detailed-data api as a searchParam. this api returns data on basis
	 * country, city, languages as a array and the same will be mounted on the UI
	 * 
	 * @param searchParam
	 * @return
	 * @throws SQLException
	 */
	@GetMapping("/detailed-data/{session}")
	public GenericResponse<GenericResponseModel<DetailedResponse<CountriesDetails<List<Country>>, CitiesDetails<List<City>>, LanguageDetails<List<LanguageCountry>>>, Object>> getDetailedData(
			@PathVariable String session, String searchParam) throws SQLException {
		logger.info("Session is "+session);
		if (dashBoardService.validateSession(session.trim())) {
			try (Connection conn = connectionPool.getConnection()) {
				Optional<DetailedResponse<CountriesDetails<List<Country>>, CitiesDetails<List<City>>, LanguageDetails<List<LanguageCountry>>>> optionalDetailedResponse = dashBoardService
						.getDetailedListOfSearchedData(searchParam, conn);
				if (optionalDetailedResponse.isPresent())
					return new GenericResponse<>(new GenericResponseModel<>(optionalDetailedResponse.get(), null),
							HttpStatus.OK);
				throw new NoDataFoundException("No Data Found");
			}
		} else {
			throw new UnauthorizedException("Invalid session found");
		}

	}

	/***
	 * 
	 * @param countryCode
	 * @return
	 * @throws SQLException
	 */
	@GetMapping("/country/{session}")
	public GenericResponse<GenericResponseModel<DetailedResponse<Country, CitiesDetails<List<City>>, LanguageDetails<List<LanguageCountry>>>, ErrorResponse>> getCountryDetails(
			@PathVariable String session, String countryCode) throws SQLException {
		logger.info("session is "+session);
		if (dashBoardService.validateSession(session)) {
			try (Connection conn = connectionPool.getConnection()) {
				Optional<DetailedResponse<Country, CitiesDetails<List<City>>, LanguageDetails<List<LanguageCountry>>>> optionalCountryDetails = dashBoardService
						.getDetailsByCountryCode(countryCode, conn);
				if (optionalCountryDetails.isPresent()) {
					return new GenericResponse<>(new GenericResponseModel<>(optionalCountryDetails.get(), null),
							HttpStatus.OK);
				}
			}
			throw new NoDataFoundException("Invalid Country Code");
		} else {
			throw new UnauthorizedException("Invalid session found");
		}
	}

}
