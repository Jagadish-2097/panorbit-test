package com.panorbit.test.model;

public class ResponseModel<C, R> {
	private C count;
	private R reponse;
	public ResponseModel(C count, R reponse) {
		super();
		this.count = count;
		this.reponse = reponse;
	}
	public C getCount() {
		return count;
	}
	public void setCount(C count) {
		this.count = count;
	}
	public R getReponse() {
		return reponse;
	}
	public void setReponse(R reponse) {
		this.reponse = reponse;
	}
	
}
