package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email) && isAmountValid(startingBalance)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address or starting balance is invalid");
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
     * if amount is negative or more than 3 decimal places, throw IllegalArgumentException
     * if amount is larger than balance, throw InsufficientFundsException
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Must withdraw positive amount: " + amount);
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

    /*
    Returns true if the amount is positive and has no more than 2 decimal places, false if otherwise
    */
    public static boolean isAmountValid(double amnt){
        if (amnt > 0){
            String[] div = Double.toString(amnt).split("\\.");
            if (div[1].length() <= 2){
                return true;
            }
        }
        return false;
    }

    /*
    if amnt is valid, adds amnt to the balance
    throws IllegalArgumentException if amnt is invalid
    */
    public void deposit(double amnt){
        if (isAmountValid(amnt)){
            this.balance += amnt;
        }else{
            throw new IllegalArgumentException("invalid amnt");
        }
    }

    /*
    if amnt is valid and the home account can afford the transfer, balance is updated in each account
    throws IllegalArgumentException if amnt is invalid
    throws InsufficientFundsException if home account cannot afford the transfer
     */
    public void transfer(double amnt, BankAccount account){
        
    }
}