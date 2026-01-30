package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        //valid withdrawal: amnt is less than balance but greater than 0
        bankAccount.withdraw(100); 
        assertEquals(100, bankAccount.getBalance(), 0.001);
        //valid withdrawal, boundary case: amnt is equal to balance
        bankAccount.withdraw(100); 
        assertEquals(0, bankAccount.getBalance(), 0.001);
        //valid withdrawal, boundary case: amnt is equal to 0
        bankAccount.withdraw(0); 
        assertEquals(0, bankAccount.getBalance(), 0.001);

        //invalid withdrawal, boundary case: amnt is larger than balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        //invalid withdrawal, boundary case: amnt is negative
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(-300));


    }

    @Test
    void isEmailValidTest(){
        //Valid Cases:
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address -- normal case -- class: valid emails
        assertTrue(BankAccount.isEmailValid( "a@bb.cc"));  //valid -- minimal length domain -- border case -- class: valid emails

        //Invalid Cases:
        assertFalse(BankAccount.isEmailValid(""));         // empty string -- border case -- class: missing @
        assertFalse(BankAccount.isEmailValid("testemail.com")); //no @ sign -- border case -- class: missing @
        assertFalse(BankAccount.isEmailValid("@hotmail.com")); //no prefix before @ sign -- border case -- class: invalid prefix
        assertFalse(BankAccount.isEmailValid("hotmail.com@jbob")); //domain and prefix are swapped -- border case -- class: invalid domain
        assertFalse(BankAccount.isEmailValid("a$$a@gmail.com")); //invalid characters -- border case -- class: special characters
        assertFalse(BankAccount.isEmailValid("jbob@jimbo.h")); //invalid domain -- border case -- class: invalid domain

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}