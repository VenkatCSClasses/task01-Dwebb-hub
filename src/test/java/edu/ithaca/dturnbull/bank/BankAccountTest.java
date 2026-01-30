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
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address -- normal case -- class: valid emails
        assertFalse(BankAccount.isEmailValid(""));         // empty string -- border case -- class: missing @
        assertTrue(BankAccount.isEmailValid( "a@bb.cc"));  //valid -- minimal length domain -- border case -- class: valid emails
        assertFalse(BankAccount.isEmailValid("testemail.com")); //no @ sign -- border case -- class: missing @
        assertFalse(BankAccount.isEmailValid("@hotmail.com")); //no prefix before @ sign -- border case -- class: missing prefix
        assertFalse(BankAccount.isEmailValid("hotmail.com@jbob")); //domain and prefix are swapped -- border case -- class: invalid domain
        assertFalse(BankAccount.isEmailValid("a$$a@gmail.com")); //invalid characters -- border case -- class: invalid characters
        assertFalse(BankAccount.isEmailValid("jbob@jimbo.h")); //invalid domain -- border case -- class: invalid domain
        // I would recommend adding a test for null input, multiple @ signs, and the case of no text between the @ and the .
        // Although I get why the empty string test is present, it could actually be a redundant test if an email will always be invalid when it does not have an @ symbol.
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