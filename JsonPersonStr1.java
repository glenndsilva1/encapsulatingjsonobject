package com.servlet;

import java.util.function.Supplier;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonPersonStr1 {
    String jsonString = "{\"name\":\"John Doe\",\"age\":30,\"city\":\"New York\"}";
    private JSONObject json;
    Supplier<String> name1;
    Supplier<String> age1;
    Supplier<String> city1;
    
    public JsonPersonStr1(String jsonstring) throws JSONException {
    	json = (JSONObject) new JSONObject(jsonString);
	 	name1 = () -> {
		try {
			return json.getString("name");
		} catch (JSONException e) {
            throw new RuntimeException("Error retrieving age from JSON", e);
		}
	};
	 age1 =  () -> {
		try {
			return String.valueOf(json.getInt("age")); // Returns age as a string
		} catch (JSONException e) {
            throw new RuntimeException("Error retrieving age from JSON", e);
		}
	};
	
	 city1 = () -> {
		try {
			return json.getString("city");
		} catch(JSONException e){
			throw new RuntimeException("Error retrieving age from JSON", e);
		}
	};
    }
    
    public String name() {
    	return this.name1.get();
    }
    
    public String age() {
    	return this.age1.get();
    }
    
    public String city() {
    	return this.city1.get();
    }
    
    public static void main(String args[]) {
			JsonPersonStr1 p;
			try {
				p = new JsonPersonStr1("name:John Doe,age:30,city:New York}");
				System.out.println("Name : " + p.name());
				System.out.println("Age : " + p.age());
				System.out.println("City : " + p.city());
			} catch (JSONException e) {
				e.printStackTrace();
			}
    }
}
