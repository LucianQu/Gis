package com.bojian.gis.net;

public class ExceptionInfo {

	public int state;

	public Exception exception;

	public ExceptionInfo(int state, Exception e) {
		this.state = state;
		this.exception = e;
	}

	@Override
	public String toString() {
		return "ExceptionInfo ErrorState=" + state + " Exception = "
				+ exception;
	}
}
