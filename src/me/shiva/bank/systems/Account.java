package me.shiva.bank.systems;

public abstract class Account {
    private double balance = 0.0;
    private String pin = null;
    // PROTOTYPE NOT MUCH THOUGHT
    public abstract double withdraw(double amount);
    public final boolean validatePin(String pin){
        return false;
    }
    public final void deposit(double amount){
        if (amount <= 0) return;
        balance += amount;
    }
    
    public double getBalance(){
        return this.balance;
    }
    
    protected void setBalance(double updated){
        balance = updated;
    }
    
    public void setPin(String pin){

    }
    
}
