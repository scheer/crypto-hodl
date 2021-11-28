package net.muchoviento.cryptohodl.model;

import java.util.Comparator;

public class PortfolioSummaryItemComparator implements Comparator<PortfolioSummaryItem> {

	@Override
	public int compare(PortfolioSummaryItem o1, PortfolioSummaryItem o2) {
		return o2.getCurrentValue().compareTo(o1.getCurrentValue());
	}

}
