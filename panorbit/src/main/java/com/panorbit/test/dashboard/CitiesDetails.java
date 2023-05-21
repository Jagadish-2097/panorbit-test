package com.panorbit.test.dashboard;

public class CitiesDetails<S> {
	private Integer count;
	private S cityDetails;

	public CitiesDetails(Integer count, S cityDetails) {
		this.count = count;
		this.cityDetails = cityDetails;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public S getCityDetails() {
		return cityDetails;
	}

	public void setCityDetails(S cityDetails) {
		this.cityDetails = cityDetails;
	}

}
