package com.panorbit.test.dashboard;

public class CountriesDetails<C> {
	private Integer count;
	private C countryDetails;

	public CountriesDetails(Integer count, C countryDetails) {
		this.count = count;
		this.countryDetails = countryDetails;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public C getCountryDetails() {
		return countryDetails;
	}

	public void setCountryDetails(C countryDetails) {
		this.countryDetails = countryDetails;
	}

}
