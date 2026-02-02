package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * if amount is negative, throw InsufficientFundsException
     * if amount is larger than balance, throw InvalidWithdrawalAmountError
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount < 0.01){
            throw new InsufficientFundsException("Must withdraw positive amount: " + amount);
        }
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        String invalidChars = " !$%^&*()+=[]\\{}|;':\",<>/?`~";
        if(email == null || email.length() == 0){
            return false;
        }
        if (email.contains("@") == false || email.contains(".") == false){
            return false;
        }
        if (email.chars().anyMatch(c -> invalidChars.indexOf(c) >= 0)){
            return false;
        }
        String[] parts = email.split("@");
        if (parts.length != 2){
            return false;
        }
        String prefix = parts[0];
        String domain = parts[1];
        if (prefix.length() == 0 || domain.length() == 0){
            return false;
        }
        if (domain.contains(".") == false){
            return false;
        }
        String[] domainParts = domain.split("\\.");
        if (domainParts.length < 2){
            return false;
        }
        if (domainParts[1].length() < 2){
            return false;
        }
        else {
            return true;
        }
    }
}