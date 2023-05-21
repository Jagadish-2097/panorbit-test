package com.panorbit.test.dashboard;

public class LanguageCountry {
	private String CountryCode;
	private String Language;
	private String IsOfficial;
	private Float Percentage;

	public LanguageCountry(String countryCode, String language, String isOfficial, Float percentage) {
		CountryCode = countryCode;
		Language = language;
		IsOfficial = isOfficial;
		Percentage = percentage;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getIsOfficial() {
		return IsOfficial;
	}

	public void setIsOfficial(String isOfficial) {
		IsOfficial = isOfficial;
	}

	public Float getPercentage() {
		return Percentage;
	}

	public void setPercentage(Float percentage) {
		Percentage = percentage;
	}

}
