


/**********************************************************************************/
/*** THIS FILE HAS BEEN AUTOMATICALLY GENERATED BY THE PANICKAPPS API GENERATOR ***/

/*                It is HIGHLY suggested that you do not edit this file.          */

//  DATABASE:     panayiota_easybusiness
//  FILE:         customerproducts.java
//  TABLE:        customerproducts
//  DATETIME:     2018-02-10 12:04:41pm
//  DESCRIPTION:  N/A

/**********************************************************************************/

package com.easybusiness.eb_androidapp.Entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class CustomerProducts implements Serializable {


	//-------------------- Supporting Finals --------------------

	final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	//-------------------- Attributes --------------------

    private int ID;
    private int CustomerID;
    private int ProductID;

	//-------------------- Constructor --------------------

    public CustomerProducts(
		int ID, 
		int CustomerID, 
		int ProductID
		) {
        this.ID = ID;
		this.CustomerID = CustomerID;
		this.ProductID = ProductID;
    }

	//-------------------- Getter Methods --------------------

	/**
     * @return int
     */
     public int getID() { return this.ID; }

	/**
     * @return int
     */
     public int getCustomerID() { return this.CustomerID; }

	/**
     * @return int
     */
     public int getProductID() { return this.ProductID; }


	//-------------------- Setter Methods --------------------

	/**
     * @param value int(11)
     */
     public void setCustomerID(int value) { this.CustomerID = value; }

	/**
     * @param value int(11)
     */
     public void setProductID(int value) { this.ProductID = value; }


	//-------------------- JSON Generation Methods --------------------

    /**
     * Specifies how objects of this class should be converted to JSON format.
     * @return String
     */
    public String toJSON() {
        return "\r\n{\r\n\t\"ID\": " + this.ID + ",\r\n\t\"CustomerID\": " + this.CustomerID + ",\r\n\t\"ProductID\": " + this.ProductID + "\r\n}";
    }
    
    /**
     * Converts an array of CustomerProducts objects to a JSON Array.
     * @param customerProducts_array
     * @return String
     */
    public static String toJSONArray(CustomerProducts[] customerProducts_array) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final CustomerProducts i : customerProducts_array) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts an ArrayList of CustomerProducts objects to a JSON Array.
     * @param customerProducts_arraylist ArrayList of CustomerProducts to convert to JSON.
     * @return String
     */
    public static String toJSONArray(ArrayList<CustomerProducts> customerProducts_arraylist) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final CustomerProducts i : customerProducts_arraylist) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts an Vector of CustomerProducts objects to a JSON Array.
     * @param customerProducts_vector Vector of CustomerProducts to convert to JSON.
     * @return String
     */
    public static String toJSONArray(Vector<CustomerProducts> customerProducts_vector) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final CustomerProducts i : customerProducts_vector) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts a List of CustomerProducts objects to a JSON Array.
     * @param customerProducts_list List of CustomerProducts to convert to JSON.
     * @return String
     */
    public static String toJSONArray(List<CustomerProducts> customerProducts_list) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final CustomerProducts i : customerProducts_list) {
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

