package net.muchoviento.cryptohodl.model;

import java.math.BigDecimal;

public class PortfolioSummaryItem {
	
	private CryptoSymbol cryptoSymbol;
	
	private BigDecimal currentValue;
	
	private BigDecimal currentAmount;
	
	private BigDecimal currentPrice;
	
	private BigDecimal currentInvestment;
	
	private BigDecimal currentRiseOrFall;

	public CryptoSymbol getCryptoSymbol() {
		return cryptoSymbol;
	}

	public void setCryptoSymbol(CryptoSymbol cryptoSymbol) {
		this.cryptoSymbol = cryptoSymbol;
	}

	public BigDecimal getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(BigDecimal currentValue) {
		this.currentValue = currentValue;
	}

	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public BigDecimal getCurrentInvestment() {
		return currentInvestment;
	}

	public void setCurrentInvestment(BigDecimal currentInvestment) {
		this.currentInvestment = currentInvestment;
	}

	public BigDecimal getCurrentRiseOrFall() {
		return currentRiseOrFall;
	}

	public void setCurrentRiseOrFall(BigDecimal currentRiseOrFall) {
		this.currentRiseOrFall = currentRiseOrFall;
	}

}
