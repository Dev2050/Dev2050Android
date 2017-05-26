package com.wolt.fissha.openinghours;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;


/**
 * Created by Fissha on 25/05/2017.
 */

public class WoltMainActivity extends AppCompatActivity {

private  WoltProperties woltProperties;
public ArrayList<HashMap<String, String>> openingHoursArrayList;
public HashMap<String, String> openingHoursList;
private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        woltProperties = new WoltProperties();
        openingHoursArrayList = new ArrayList<HashMap<String, String>>();
        openingHoursList = new HashMap<String, String>();
        lv = (ListView) findViewById(R.id.list);
        new GetOpeningHours().execute();
    }


    public void parseJSONArray(JSONArray jsonArray, String day){
        if(jsonArray.length()!=0){
            try{
                for(int i = 0; i<jsonArray.length(); i++){
                    JSONObject jsonInsider = jsonArray.getJSONObject(i);

                    String theType = jsonInsider.getString("type");
                    woltProperties.setTheType(theType);
                    if(jsonArray.length()==1 && theType.equalsIgnoreCase("open")){
                        woltProperties.setJsonArrayLengthEqOne(true);
                        woltProperties.setSpecialDay(day);
                    }
                    else if(jsonArray.length()==1 && theType.equalsIgnoreCase("close")){
                        woltProperties.setJsonArrayLengthEqOne(false);
                    }
                    else if((theType.equalsIgnoreCase("close") && woltProperties.getJsonArrayLengthEqOne())){
                        String closingTime = jsonInsider.getString("value");
                        woltProperties.setClosingTime(closingTime);
                        String specialDay = woltProperties.getSpecialDay();
                        specialDayOperation(specialDay);

                        if(theType.equalsIgnoreCase("close") && jsonArray.length() == 2){
                            closingTime = jsonInsider.getString("value");
                            woltProperties.setClosingTime(closingTime);
                            commonTask(day);
                        }
                    }
                    else if(theType.equalsIgnoreCase("close") && jsonArray.length() == 2){
                        String closingTime = jsonInsider.getString("value");
                        woltProperties.setClosingTime(closingTime);
                        commonTask(day);
                    }
                    else if(theType.equalsIgnoreCase("close") && jsonArray.length()>2 && woltProperties.getJsonArrayLengthGrTwo()){
                        String closingTime = jsonInsider.getString("value");
                        woltProperties.setClosingTime(closingTime);
                        commonTask(day);
                    }
                    else if(theType.equalsIgnoreCase("open") && jsonArray.length() != 1){
                        if(jsonArray.length()>2){
                            woltProperties.setJsonArrayLengthGrTwo(true);
                        }
                        String openingTime = jsonInsider.getString("value");
                        woltProperties.setOpeningTime(openingTime);

                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        }else{
            defaultOpeningStatus(day);
        }
    }


    public void defaultOpeningStatus(String day){
        openingHoursList = new HashMap<String, String>();
        woltProperties.setNewDay(day.concat(": ").concat("Closed"));
        openingHoursList.put("status", woltProperties.getNewDay());
        openingHoursArrayList.add(openingHoursList);

    }
    public void commonTask(String day){
        String openingTime = woltProperties.getOpeningTime();
        String closingTime = woltProperties.getClosingTime();

        woltProperties.setNewDay(day.concat(": ").concat(openingTime).concat(" - ").concat(closingTime));
        //Adding the values into the ArrayList
        openingHoursList = new HashMap<String, String>();
        openingHoursList.put("status", woltProperties.getNewDay());
        openingHoursArrayList.add(openingHoursList);

    }
    public void specialDayOperation(String specialDay){
        String openingTime = woltProperties.getOpeningTime();
        String closingTime = woltProperties.getClosingTime();
        String theSpecialDay = specialDay.concat(": ").concat(openingTime).concat(" - ").concat(closingTime);
        //Adding the values into the ArrayList
        openingHoursList = new HashMap<String, String>();
        openingHoursList.put("status", theSpecialDay);
        openingHoursArrayList.add(openingHoursList);
        woltProperties.setJsonArrayLengthEqOne(false);
    }

    public String parseJSON(){
        String json = null;
        try{
            InputStream istream = getAssets().open("openinghours.json");
            int size = istream.available();
            byte[] buffer = new byte[size];
            istream.read(buffer);
            istream.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    private class GetOpeningHours extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(WoltMainActivity.this, "If json stopped, please restart the app!", Toast.LENGTH_LONG).show();
        }
        @Override
        protected Void doInBackground(Void... arg0){
            try{
                JSONObject jsonObject = new JSONObject(parseJSON());
                JSONArray jsonArray1 = jsonObject.getJSONArray("monday");
                parseJSONArray(jsonArray1, "Monday");
                JSONArray jsonArray2 = jsonObject.getJSONArray("tuesday");
                parseJSONArray(jsonArray2, "Tuesday");
                JSONArray jsonArray3 = jsonObject.getJSONArray("wednesday");
                parseJSONArray(jsonArray3, "Wednesday");
                JSONArray jsonArray4 = jsonObject.getJSONArray("thursday");
                parseJSONArray(jsonArray4,"Thursday");
                JSONArray jsonArray5 = jsonObject.getJSONArray("friday");
                parseJSONArray(jsonArray5, "Friday");
                JSONArray jsonArray6 = jsonObject.getJSONArray("saturday");
                parseJSONArray(jsonArray6, "Saturday");
                JSONArray jsonArray7 = jsonObject.getJSONArray("sunday");
                parseJSONArray(jsonArray7, "Sunday");
            }catch (JSONException e){
                e.printStackTrace();
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(WoltMainActivity.this, openingHoursArrayList,
                    R.layout.list_item, new String[] {"status"},
                    new int[]{R.id.status});
            lv.setAdapter(adapter);
        }
    }

}
