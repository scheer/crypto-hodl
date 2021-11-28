package net.muchoviento.cryptohodl.controller;

import java.io.Serializable;
import java.math.BigDecimal;

import net.muchoviento.cryptohodl.model.CryptoSymbol;

public class PurchaseDto implements Serializable {

    private CryptoSymbol symbol;
	
	private BigDecimal amount;
	
	private BigDecimal totalFee;
    
    private String dateTime;

    public CryptoSymbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "PurchaseDto [amount=" + amount + ", dateTime=" + dateTime + ", symbol=" + symbol + ", totalFee="
                + totalFee + "]";
    }

    public void setSymbol(CryptoSymbol symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    
}
