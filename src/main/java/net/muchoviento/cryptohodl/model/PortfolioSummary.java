package net.muchoviento.cryptohodl.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PortfolioSummary {	

	/**
	 * All our coins, sorted by currentValue
	 */
	private List<PortfolioSummaryItem> items;
	
	private BigDecimal currentValue;
	
	private BigDecimal currentInvestment;
	
	public List<PortfolioSummaryItem> getItems() {
		return items;
	}

	public void setItems(List<PortfolioSummaryItem> items) {
		this.items = items;
	}

	public BigDecimal getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(BigDecimal currentValue) {
		this.currentValue = currentValue;
	}

	public BigDecimal getCurrentInvestment() {
		return currentInvestment;
	}

	public void setCurrentInvestment(BigDecimal currentInvestment) {
		this.currentInvestment = currentInvestment;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\n\n");
		for (PortfolioSummaryItem item : items) {
			sb.append(item.getCryptoSymbol().name + " - " + format(item.getCurrentValue()) + " EUR (" + format(item.getCurrentRiseOrFall()) + " %)\n");
		}
		sb.append("--------------------\n");
		sb.append("Value - " + format(currentValue) + " EUR");
		sb.append("\n\n");
		return sb.toString();
	}
	
	private String format(final BigDecimal amount) {
		return String.format("%,.0f", amount.setScale(2, RoundingMode.DOWN)); 
	}


}
