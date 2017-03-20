package com.bojian.gis.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

public class CustomDatePickerDialog extends DatePickerDialog{

	public CustomDatePickerDialog(Context context,
			OnDateSetListener callBack, int year, int monthOfYear,
			int dayOfMonth) {
		super(context, callBack, year, monthOfYear, dayOfMonth);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onDateChanged(DatePicker view, int year, int month, int day) {
		// TODO Auto-generated method stub
		super.onDateChanged(view, year, month, day);
	}
}
