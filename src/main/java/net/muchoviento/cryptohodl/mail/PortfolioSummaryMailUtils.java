package net.muchoviento.cryptohodl.mail;

import java.math.BigDecimal;

public final class PortfolioSummaryMailUtils {
	
	private PortfolioSummaryMailUtils() {
		// util class
	}
	
	public static String generateMailBody(final BigDecimal currentValue, final BigDecimal currentInvestment) {
		final StringBuffer sb = new StringBuffer();
		sb.append("Dear Cryptovestor, \n\n");
		sb.append("here comes your montly portfolio summary: \n");
		sb.append("You invested " + currentInvestment + " EUR so far. \n");
		sb.append("The current portfolio value is " + currentValue + " EUR. \n\n");
		sb.append("Sincerely, your crypohodl application");		
		return sb.toString();
	}

}
