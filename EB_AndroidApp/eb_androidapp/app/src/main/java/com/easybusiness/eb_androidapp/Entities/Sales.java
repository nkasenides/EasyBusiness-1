


/**********************************************************************************/
/*** THIS FILE HAS BEEN AUTOMATICALLY GENERATED BY THE PANICKAPPS API GENERATOR ***/

/*                It is HIGHLY suggested that you do not edit this file.          */

//  DATABASE:     panayiota_easybusiness
//  FILE:         sales.java
//  TABLE:        sales
//  DATETIME:     2018-02-10 12:04:41pm
//  DESCRIPTION:  N/A

/**********************************************************************************/

package com.easybusiness.eb_androidapp.Entities;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Sales implements Serializable {


	//-------------------- Supporting Finals --------------------

	final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	//-------------------- Attributes --------------------

    private int ID;
    private int CustomerID;
    private int SaleProductsID;
    private double Tax;
    private long SaleTimeDate;

	//-------------------- Constructor --------------------

    public Sales(
		int ID, 
		int CustomerID, 
		int SaleProductsID, 
		double Tax, 
		long SaleTimeDate
		) {
        this.ID = ID;
		this.CustomerID = CustomerID;
		this.SaleProductsID = SaleProductsID;
		this.Tax = Tax;
		this.SaleTimeDate = SaleTimeDate;
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
     public int getSaleProductsID() { return this.SaleProductsID; }

	/**
     * @return double
     */
     public double getTax() { return this.Tax; }

	/**
     * @return int
     */
     public long getSaleTimeDate() { return this.SaleTimeDate; }


	//-------------------- Setter Methods --------------------

	/**
     * @param value int(10) unsigned
     */
     public void setCustomerID(int value) { this.CustomerID = value; }

	/**
     * @param value int(10) unsigned
     */
     public void setSaleProductsID(int value) { this.SaleProductsID = value; }

	/**
     * @param value double
     */
     public void setTax(double value) { this.Tax = value; }

	/**
     * @param value int(13)
     */
     public void setSaleTimeDate(long value) { this.SaleTimeDate = value; }


	//-------------------- JSON Generation Methods --------------------

    /**
     * Specifies how objects of this class should be converted to JSON format.
     * @return String
     */
    public String toJSON() {
        return "\r\n{\r\n\t\"ID\": " + this.ID + ",\r\n\t\"CustomerID\": " + this.CustomerID + ",\r\n\t\"SaleProductsID\": " + this.SaleProductsID + ",\r\n\t\"Tax\": " + this.Tax + ",\r\n\t\"SaleTimeDate\": " + this.SaleTimeDate + "\r\n}";
    }
    
    /**
     * Converts an array of Sales objects to a JSON Array.
     * @param sales_array
     * @return String
     */
    public static String toJSONArray(Sales [] sales_array) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Sales i : sales_array) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts an ArrayList of Sales objects to a JSON Array.
     * @param sales_arraylist ArrayList of Sales to convert to JSON.
     * @return String
     */
    public static String toJSONArray(ArrayList<Sales> sales_arraylist) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Sales i : sales_arraylist) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts an Vector of Sales objects to a JSON Array.
     * @param sales_vector Vector of Sales to convert to JSON.
     * @return String
     */
    public static String toJSONArray(Vector<Sales> sales_vector) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Sales i : sales_vector) {
            strArray.append(i.toJSON());
            strArray.append(", ");
        }
        strArray = new StringBuilder(strArray.substring(0, strArray.length() - 3));
        strArray.append("} ] ");
        return strArray.toString();
    }
    
    /**
     * Converts a List of Sales objects to a JSON Array.
     * @param sales_list List of Sales to convert to JSON.
     * @return String
     */
    public static String toJSONArray(List<Sales> sales_list) {
        StringBuilder strArray = new StringBuilder("[ ");
        for (final Sales i : sales_list) {
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

