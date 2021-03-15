package com.meritamerica.assignment3;

public class CDOffering {
	//Instance variables
    private double interestRate;
    private int term;

    //Constructor
    CDOffering(int term, double interestRate) {
        this.interestRate = interestRate;
        this.term = term;
    }

    /*
     * Getters
     */
    
    int getTerm() {
        return this.term;
    }

    double getInterestRate() {
        return this.interestRate;
    }

    //Read method
    public static CDOffering readFromString(String cdOfferingDataString) {
        String[] str = cdOfferingDataString.split(",");
        return new CDOffering(Integer.parseInt(str[0]), Double.parseDouble(str[1]));
    }
    
    //Write method
    public String writeToString() {
        String result = this.interestRate + "," + this.term;
        return result;
    }

}