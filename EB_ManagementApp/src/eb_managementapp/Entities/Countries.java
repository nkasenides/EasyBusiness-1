


/**********************************************************************************/
/*** THIS FILE HAS BEEN AUTOMATICALLY GENERATED BY THE PANICKAPPS API GENERATOR ***/

/*                It is HIGHLY suggested that you do not edit this file.          */

//  DATABASE:     panayiota_easybusiness
//  FILE:         countries.java
//  TABLE:        countries
//  DATETIME:     2018-02-10 12:04:41pm
//  DESCRIPTION:  N/A

/**********************************************************************************/
			
package eb_managementapp.Entities;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Countries implements Serializable {


	//-------------------- Supporting Finals --------------------

	final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	//-------------------- Attributes --------------------

    private int ID;
    private String Name;

	//-------------------- Constructor --------------------

    public Countries(
		int ID, 
		String Name
		) {
        this.ID = ID;
		this.Name = Name;
    }

	//-------------------- Getter Methods --------------------

	/**
     * @return int
     */
     public int getID() { return this.ID; }

	/**
     * @return String
     */
     public String getName() { return this.Name; }


	//-------------------- Setter Methods --------------------

	/**
     * @param value varchar(255)
     */
     public void setName(String value) { this.Name = value; }


	//-------------------- JSON Generation Methods --------------------

    /**
     * Specifies how objects of this class should be converted to JSON format.
     * @return String
     */
    public String toJSON() {
        return "\r\n{\r\n\t\"ID\": " + this.ID + ",\r\n\t\"Name\": \"" + this.Name+ "\"\r\n}";
    }
    
    /**
     * Converts an array of Countries objects to a JSON Array.
     * @param countries_array
     * @return String
     */
    public static String toJSONArray(Countries [] countries_array) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Countries i : countries_array) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts an ArrayList of Countries objects to a JSON Array.
     * @param countries_arraylist ArrayList of Countries to convert to JSON.
     * @return String
     */
    public static String toJSONArray(ArrayList<Countries> countries_arraylist) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Countries i : countries_arraylist) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts an Vector of Countries objects to a JSON Array.
     * @param countries_vector Vector of Countries to convert to JSON.
     * @return String
     */
    public static String toJSONArray(Vector<Countries> countries_vector) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Countries i : countries_vector) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts a List of Countries objects to a JSON Array.
     * @param countries_list List of Countries to convert to JSON.
     * @return String
     */
    public static String toJSONArray(List<Countries> countries_list) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Countries i : countries_list) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    @Override
    public String toString() {
        return toJSON();
    }

}
