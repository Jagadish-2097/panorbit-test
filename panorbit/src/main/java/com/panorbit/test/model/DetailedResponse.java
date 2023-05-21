package com.panorbit.test.model;

public class DetailedResponse<C, S, L> {
	private C country;
	private S city;
	private L language;

	public DetailedResponse(C country, S city, L language) {
		this.country = country;
		this.city = city;
		this.language = language;
	}

	public C getCountry() {
		return country;
	}

	public void setCountry(C country) {
		this.country = country;
	}

	public S getCity() {
		return city;
	}

	public void setCity(S city) {
		this.city = city;
	}

	public L getLanguage() {
		return language;
	}

	public void setLanguage(L language) {
		this.language = language;
	}

}
