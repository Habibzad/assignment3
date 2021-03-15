package com.meritamerica.assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;

public class AccountHolder implements Comparable<AccountHolder> {
	//
    public static final long BALANCE_LIMIT = 250000;

    private String firstName;
    private String middleName;
    private String lastName;
    private String ssn;
    
    private CheckingAccount[] checkAccounts = new CheckingAccount[0];
    private SavingsAccount[] saveAccounts = new SavingsAccount[0];
    private CDAccount[] cdAccounts = new CDAccount[0];
    
    private static long nextAccountNumber;

    //Constructor
    public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setSSN(ssn);
        nextAccountNumber = 0;
    }

    /*
     * Getters
     */
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getMiddleName() {
        return this.middleName;
    }

    public String getLastName() {
        return this.lastName;
    }
    
    public String getSSN() {
        return this.ssn;
    }
    
    /*
     * Setters
     */
    
    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    private void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setSSN(String ssn) {
        this.ssn = ssn;
    }

    //Add Checking Account
    public CheckingAccount addCheckingAccount(double openingBalance) {
        CheckingAccount checkingAccount = new CheckingAccount(openingBalance);

        if (getCombinedBalance() + openingBalance >= BALANCE_LIMIT) {
            return null;
        } else {
            return addCheckingAccount(checkingAccount);
        }

    }
    
    public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
        if (getCombinedBalance() + checkingAccount.getBalance() >= BALANCE_LIMIT) {
            return null;
        } else {
            CheckingAccount[] checkingAccountsArray = new CheckingAccount[checkAccounts.length + 1];
            int i;
            for (i = 0; i < checkAccounts.length; i++) {
            	checkingAccountsArray[i] = checkAccounts[i];
            }

            checkingAccountsArray[i] = checkingAccount;
            checkAccounts = checkingAccountsArray;
            return checkingAccount;
        }
    }

    public CheckingAccount[] getCheckingAccounts() {
        return checkAccounts;
    }

    
    public int getNumberOfCheckingAccounts() {
        return this.checkAccounts.length;
    }

    public double getCheckingBalance() {
        double total = 0;
        for (int i = 0; i < checkAccounts.length; i++) {
            total += checkAccounts[i].getBalance();
        }
        return total;
    }

    public SavingsAccount addSavingsAccount(double openingBalance) {
        SavingsAccount savingAccount = new SavingsAccount(openingBalance);

        if (getCombinedBalance() + openingBalance >= BALANCE_LIMIT) {
            return null;
        } else {
            return addSavingsAccount(savingAccount);
        }
    }

    public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) {
        if (getCombinedBalance() + savingsAccount.getBalance() >= BALANCE_LIMIT) {
            return null;
        } else {
            SavingsAccount[] newArray = new SavingsAccount[saveAccounts.length + 1];
            int i;
            for (i = 0; i < saveAccounts.length; i++) {
                newArray[i] = saveAccounts[i];
            }
            newArray[i] = savingsAccount;
            saveAccounts = newArray;
            return savingsAccount;
        }
    }

    public SavingsAccount[] getSavingsAccounts() {
        return this.saveAccounts;
    }

    public int getNumberOfSavingsAccounts() {
        return this.saveAccounts.length;
    }

    public double getSavingsBalance() {
        double total = 0;

        for (int i = 0; i < saveAccounts.length; i++) {
            total += saveAccounts[i].getBalance();
        }

        return total;
    }

    public CDAccount addCDAccount(CDAccount cdAccount) {
        CDAccount[] newArray = new CDAccount[cdAccounts.length + 1];
        int i;
        for (i = 0; i < cdAccounts.length; i++) {
            newArray[i] = cdAccounts[i];
        }
        newArray[i] = cdAccount;
        cdAccounts = newArray;
        return cdAccount;
    }

    public CDAccount addCDAccount(CDOffering offering, double openingBalance) {
        CDAccount newName = new CDAccount(offering, openingBalance);
        return addCDAccount(newName);
    }

    public CDAccount[] getCDAccounts() {
        return this.cdAccounts;
    }

    public int getNumberOfCDAccounts() {
        return this.cdAccounts.length;
    }

    public double getCDBalance() {
        double total = 0;

        for (int i = 0; i < cdAccounts.length; i++) {
            total += cdAccounts[i].getBalance();
        }

        return total;
    }

    public double getCombinedBalance() {
        double total = 0;

        for (int i = 0; i < checkAccounts.length; i++) {
            total += checkAccounts[i].getBalance();
        }

        for (int i = 0; i < saveAccounts.length; i++) {
            total += saveAccounts[i].getBalance();
        }

        for (int i = 0; i < cdAccounts.length; i++) {
            total += cdAccounts[i].getBalance();
        }

        return total;
    }

    public String toString() {
        return "Name: " + this.firstName + " " + this.middleName + " " + this.lastName + "\n" + "SSN: " + this.ssn
                + "\n" + this.getCheckingAccounts().toString();
    }

    public static long getNewAccountNumber() {

        return nextAccountNumber += 1;
    }

    public static AccountHolder readFromString(String accountHolderData) throws ParseException {

        String[] newAccountHolder = accountHolderData.split(",");
        return new AccountHolder(newAccountHolder[0], newAccountHolder[1], newAccountHolder[2], newAccountHolder[3]);

    }

    @Override
    public int compareTo(AccountHolder arg0) {

        if (this.getCombinedBalance() > arg0.getCombinedBalance()) {
            return 1;
        } else {
            return -1;
        }
    }

    public String writeToString() {
        String newString = this.firstName + "," + this.middleName + "," + this.lastName + "," + this.ssn;
        return newString;
    }
}
