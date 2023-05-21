package com.panorbit.test.model;

public class GenericResponseModel<R, E> {
	private R data;
	private E error;
	public GenericResponseModel(R r, E e) {
		this.data = r;
		this.error = e;
	}
	public R getData() {
		return data;
	}
	public E getError() {
		return error;
	}
	public void setData(R data) {
		this.data = data;
	}
	public void setError(E error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "GenericResponseModel [data=" + data + ", error=" + error + "]";
	}
	
	
}
