


/**********************************************************************************/
/*** THIS FILE HAS BEEN AUTOMATICALLY GENERATED BY THE PANICKAPPS API GENERATOR ***/

/*                It is HIGHLY suggested that you do not edit this file.          */

//  DATABASE:     panayiota_easybusiness
//  FILE:         users.java
//  TABLE:        users
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


public class Users implements Serializable {


	//-------------------- Supporting Finals --------------------

	final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	//-------------------- Attributes --------------------

    private int UserID;
    private String Username;
    private String Password;
    private int UserLevelID;
    private String Firstname;
    private String Lastname;
    private int DateHired;
    private String City;
    private String Address;
    private String Telephone;
    private int CountryID;

	//-------------------- Constructor --------------------

    public Users(
		int UserID, 
		String Username, 
		String Password, 
		int UserLevelID, 
		String Firstname, 
		String Lastname, 
		int DateHired, 
		String City, 
		String Address, 
		String Telephone, 
		int CountryID
		) {
        this.UserID = UserID;
		this.Username = Username;
		this.Password = Password;
		this.UserLevelID = UserLevelID;
		this.Firstname = Firstname;
		this.Lastname = Lastname;
		this.DateHired = DateHired;
		this.City = City;
		this.Address = Address;
		this.Telephone = Telephone;
		this.CountryID = CountryID;
    }

	//-------------------- Getter Methods --------------------

	/**
     * @return int
     */
     public int getUserID() { return this.UserID; }

	/**
     * @return String
     */
     public String getUsername() { return this.Username; }

	/**
     * @return String
     */
     public String getPassword() { return this.Password; }

	/**
     * @return int
     */
     public int getUserLevelID() { return this.UserLevelID; }

	/**
     * @return String
     */
     public String getFirstname() { return this.Firstname; }

	/**
     * @return String
     */
     public String getLastname() { return this.Lastname; }

	/**
     * @return int
     */
     public int getDateHired() { return this.DateHired; }

	/**
     * @return String
     */
     public String getCity() { return this.City; }

	/**
     * @return String
     */
     public String getAddress() { return this.Address; }

	/**
     * @return String
     */
     public String getTelephone() { return this.Telephone; }

	/**
     * @return int
     */
     public int getCountryID() { return this.CountryID; }


	//-------------------- Setter Methods --------------------

	/**
     * @param value varchar(100)
     */
     public void setUsername(String value) { this.Username = value; }

	/**
     * @param value varchar(255)
     */
     public void setPassword(String value) { this.Password = value; }

	/**
     * @param value int(10) unsigned
     */
     public void setUserLevelID(int value) { this.UserLevelID = value; }

	/**
     * @param value varchar(255)
     */
     public void setFirstname(String value) { this.Firstname = value; }

	/**
     * @param value varchar(255)
     */
     public void setLastname(String value) { this.Lastname = value; }

	/**
     * @param value bigint(13)
     */
     public void setDateHired(int value) { this.DateHired = value; }

	/**
     * @param value varchar(255)
     */
     public void setCity(String value) { this.City = value; }

	/**
     * @param value varchar(255)
     */
     public void setAddress(String value) { this.Address = value; }

	/**
     * @param value varchar(255)
     */
     public void setTelephone(String value) { this.Telephone = value; }

	/**
     * @param value int(11)
     */
     public void setCountryID(int value) { this.CountryID = value; }


	//-------------------- JSON Generation Methods --------------------

    /**
     * Specifies how objects of this class should be converted to JSON format.
     * @return String
     */
    public String toJSON() {
        return "\r\n{\r\n\t\"UserID\": " + this.UserID + ",\r\n\t\"Username\": \"" + this.Username+ "\",\r\n\t\"Password\": \"" + this.Password+ "\",\r\n\t\"UserLevelID\": " + this.UserLevelID + ",\r\n\t\"Firstname\": \"" + this.Firstname+ "\",\r\n\t\"Lastname\": \"" + this.Lastname+ "\",\r\n\t\"DateHired\": " + this.DateHired + ",\r\n\t\"City\": \"" + this.City+ "\",\r\n\t\"Address\": \"" + this.Address+ "\",\r\n\t\"Telephone\": \"" + this.Telephone+ "\",\r\n\t\"CountryID\": " + this.CountryID + "\r\n}";
    }
    
    /**
     * Converts an array of Users objects to a JSON Array.
     * @param users_array
     * @return String
     */
    public static String toJSONArray(Users [] users_array) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Users i : users_array) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts an ArrayList of Users objects to a JSON Array.
     * @param users_arraylist ArrayList of Users to convert to JSON.
     * @return String
     */
    public static String toJSONArray(ArrayList<Users> users_arraylist) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Users i : users_arraylist) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts an Vector of Users objects to a JSON Array.
     * @param users_vector Vector of Users to convert to JSON.
     * @return String
     */
    public static String toJSONArray(Vector<Users> users_vector) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Users i : users_vector) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts a List of Users objects to a JSON Array.
     * @param users_list List of Users to convert to JSON.
     * @return String
     */
    public static String toJSONArray(List<Users> users_list) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Users i : users_list) {
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

