package com.namowebiz.holiday;

public class Holiday {
	private String holiday_id;
	private String holiday_name;
	private String holiday_start;
	private String holiday_end;
	private int holidays_day;
	private Boolean holiday_am;
	private Boolean holiday_pm;
		
	public int getHolidays_day() {
		return holidays_day;
	}
	public void setHolidays_day(int holidays_day) {
		this.holidays_day = holidays_day;
	}
	public String getHoliday_id() {
		return holiday_id;
	}
	public void setHoliday_id(String holiday_id) {
		this.holiday_id = holiday_id;
	}
	public String getHoliday_name() {
		return holiday_name;
	}
	public void setHoliday_name(String holiday_name) {
		this.holiday_name = holiday_name;
	}
	public String getHoliday_start() {
		return holiday_start;
	}
	public void setHoliday_start(String holiday_start) {
		this.holiday_start = holiday_start;
	}
	public String getHoliday_end() {
		return holiday_end;
	}
	public void setHoliday_end(String holiday_end) {
		this.holiday_end = holiday_end;
	}
	public Boolean getHoliday_am() {
		return holiday_am;
	}
	public void setHoliday_am(Boolean holiday_am) {
		this.holiday_am = holiday_am;
	}
	public Boolean getHoliday_pm() {
		return holiday_pm;
	}
	public void setHoliday_pm(Boolean holiday_pm) {
		this.holiday_pm = holiday_pm;
	}
	
	
}
