package com.wolt.devname.openinghours;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class WoltProperties {

    private String openingTime;
    private String closingTime;
    private String theType;
    private String theNewDay;
    private String theSpecialDay;
    private boolean jsonArrayLengthEqOne = false;
    private boolean jsonArrayLengthGrTwo = false;

    public void setJsonArrayLengthEqOne(boolean jsonArrayLengthEqOne){

        this.jsonArrayLengthEqOne = jsonArrayLengthEqOne;
    }
    public boolean getJsonArrayLengthEqOne(){

        return jsonArrayLengthEqOne;
    }
    public void setJsonArrayLengthGrTwo(boolean jsonArrayLengthGrTwo){

        this.jsonArrayLengthGrTwo = jsonArrayLengthGrTwo;
    }
    public boolean getJsonArrayLengthGrTwo(){

        return jsonArrayLengthGrTwo;
    }

    public void setSpecialDay(String theSpecialDay){

        this.theSpecialDay = theSpecialDay;
    }
    public String getSpecialDay(){

        return theSpecialDay;
    }
    public void setNewDay(String theNewDay){

        this.theNewDay = theNewDay;
    }
    public String getNewDay(){

        return theNewDay;
    }
    public void setTheType(String theType){

        this.theType = theType;
    }
    public String getTheType(){

        return theType;
    }
    public void setOpeningTime(String openingTime){
        this.openingTime = timeConverter(openingTime);
    }
    public String getOpeningTime(){

        return openingTime;
    }
    public void setClosingTime(String closingTime){
        this.closingTime = timeConverter(closingTime);
    }
    public String getClosingTime(){
        return closingTime;
    }

    public String timeConverter(String unixTime) {
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getDefault();
        long timeIs = Long.valueOf(unixTime) * 1000;
        calendar.setTimeInMillis(timeIs * 1000);
        calendar.add(Calendar.MILLISECOND, timeZone.getOffset(calendar.getTimeInMillis()));
        Date dateformat = new java.util.Date(timeIs);
        SimpleDateFormat formatToReq = new SimpleDateFormat("h a");
        formatToReq.setTimeZone(TimeZone.getTimeZone("GMT"));
        String convertedTime = formatToReq.format(dateformat);
        return convertedTime;
    }
}


