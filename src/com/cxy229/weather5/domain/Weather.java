package com.cxy229.weather5.domain;

import java.util.List;

public class Weather {
	public String error;
	public String status;
	public String date;
	public String message;
	public List<Results> results;
	
	public String getError(){
		return error;
	}
	public String getStatus(){
		return status;
	}
	public String getData(){
		return date;
	}
	
	

}
