package com.panorbit.test.dashboard;

public class LanguageDetails<L> {
	private Integer count;
	private L languageDetails;

	public LanguageDetails(Integer count, L languageDetails) {
		this.count = count;
		this.languageDetails = languageDetails;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public L getLanguageDetails() {
		return languageDetails;
	}

	public void setLanguageDetails(L languageDetails) {
		this.languageDetails = languageDetails;
	}

}
