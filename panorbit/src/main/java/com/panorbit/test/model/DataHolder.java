package com.panorbit.test.model;

public class DataHolder {
	private String placeHolder;
	private String Code;
	private String Name;

	public DataHolder(String placeHolder, String code, String name) {
		this.placeHolder = placeHolder;
		Code = code;
		Name = name;
	}

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
