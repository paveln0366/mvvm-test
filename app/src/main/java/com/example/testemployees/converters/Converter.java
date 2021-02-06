package com.example.testemployees.converters;

import androidx.room.TypeConverter;

import com.example.testemployees.pojo.Specialty;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    @TypeConverter
    public String listSpecialityToString(List<Specialty> specialties) {
        return new Gson().toJson(specialties);
//        JSONArray jsonArray = new JSONArray();
//        for (Specialty specialty : specialties) {
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("specialty_id", specialty.getSpecialtyId());
//                jsonObject.put("name", specialty.getName());
//                jsonArray.put(jsonObject);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return jsonArray.toString();
    }
    @TypeConverter
    public List<Specialty> stringToListSpeciality(String specialtiesAsString) {
        Gson gson = new Gson();
        ArrayList objects = gson.fromJson(specialtiesAsString, ArrayList.class);
        ArrayList<Specialty> specialties = new ArrayList<>();
        for(Object o : objects) {
            specialties.add(gson.fromJson(o.toString(), Specialty.class));
        }
        return specialties;
    }
}
