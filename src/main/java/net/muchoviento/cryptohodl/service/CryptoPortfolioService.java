package net.muchoviento.cryptohodl.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.muchoviento.cryptohodl.model.CryptoSymbol;
import net.muchoviento.cryptohodl.model.PortfolioSummary;
import net.muchoviento.cryptohodl.model.PortfolioSummaryItem;
import net.muchoviento.cryptohodl.model.PortfolioSummaryItemComparator;
import net.muchoviento.cryptohodl.model.Purchase;

@Service
public class CryptoPortfolioService {
	
	@Resource
	private CryptoPriceService cryptoPriceService;
	
	/**
	 * Generates an exhaustive portfolio summary.
	 */
	public PortfolioSummary generateSummary(final List<Purchase> orders) throws Exception {
		
		final PortfolioSummary result = new PortfolioSummary();
		final Set<CryptoSymbol> symbols = extractSymbols(orders);
		final Map<CryptoSymbol, BigDecimal> prices = cryptoPriceService.actualPrices(symbols);
		final List<PortfolioSummaryItem> itemList = new ArrayList<>();
		BigDecimal currentInvestmentSum = BigDecimal.ZERO;
		BigDecimal currentValueSum = BigDecimal.ZERO;
		for (CryptoSymbol symbol : symbols) {
			final BigDecimal actualPrice = prices.get(symbol);
			final BigDecimal amountFromAllOrders = getAmountFromAllOrders(symbol, orders);
			final BigDecimal investmentFromAllOrders = getInvestmentFromAllOrders(symbol, orders);
			final BigDecimal currentValue = amountFromAllOrders.multiply(actualPrice);
			final BigDecimal riseOrFall = currentValue.divide(investmentFromAllOrders, new MathContext(15)).multiply(BigDecimal.valueOf(100));
			
			final PortfolioSummaryItem item = new PortfolioSummaryItem();
			item.setCryptoSymbol(symbol);
			item.setCurrentAmount(amountFromAllOrders);
			item.setCurrentInvestment(investmentFromAllOrders);
			item.setCurrentValue(currentValue);
			item.setCurrentPrice(actualPrice);
			item.setCurrentRiseOrFall(riseOrFall);
			itemList.add(item);
			
			currentInvestmentSum = currentInvestmentSum.add(investmentFromAllOrders);
			currentValueSum = currentValueSum.add(currentValue);
		}
		
		Collections.sort(itemList, new PortfolioSummaryItemComparator());
		
		result.setItems(itemList);
		result.setCurrentInvestment(currentInvestmentSum);
		result.setCurrentValue(currentValueSum);		
		
		return result;
	}
	
	public BigDecimal currentValue(final List<Purchase> orders) throws Exception {
		BigDecimal result = BigDecimal.ZERO;
		final Set<CryptoSymbol> symbols = extractSymbols(orders);
		final Map<CryptoSymbol, BigDecimal> prices = cryptoPriceService.actualPrices(symbols);
		for (Purchase order : orders) {
			final BigDecimal actualPrice = prices.get(order.getSymbol());
			final BigDecimal currentValue = order.getAmount().multiply(actualPrice);
			result = result.add(currentValue);
		}		
		return result;
	}
	
	private Set<CryptoSymbol> extractSymbols(final List<Purchase> orders) {
		final Set<CryptoSymbol> result = new HashSet<>();
		for (Purchase order : orders) {
			result.add(order.getSymbol());
		}
		return result;
	}

	public BigDecimal currentInvestment(final List<Purchase> orders) {
		BigDecimal result = BigDecimal.ZERO;
		for (Purchase order : orders) {
			result = result.add(order.getTotalFee());
		}		
		return result;
	}
	
	public Map<CryptoSymbol, BigDecimal> currentRiseOrFall(final List<Purchase> orders) throws Exception {
		final Set<CryptoSymbol> symbols = extractSymbols(orders);
		final Map<CryptoSymbol, BigDecimal> prices = cryptoPriceService.actualPrices(symbols);
		final Map<CryptoSymbol, BigDecimal> result = new HashMap<>();
		for (CryptoSymbol symbol : symbols) {
			final BigDecimal actualPrice = prices.get(symbol);
			final BigDecimal amountFromAllOrders = getAmountFromAllOrders(symbol, orders);
			final BigDecimal investmentFromAllOrders = getInvestmentFromAllOrders(symbol, orders);
			final BigDecimal currentValue = amountFromAllOrders.multiply(actualPrice);
			final BigDecimal riseOrFall = currentValue.divide(investmentFromAllOrders, new MathContext(15)).multiply(BigDecimal.valueOf(100));
			result.put(symbol, riseOrFall);
		}					
		return result;
	}

	private BigDecimal getAmountFromAllOrders(final CryptoSymbol symbol, final List<Purchase> orders) {
		BigDecimal result = BigDecimal.ZERO;
		for (Purchase order : orders) {
			if (order.getSymbol() == symbol) {
				result = result.add(order.getAmount());
			}
		}
		return result;
	}
	
	private BigDecimal getInvestmentFromAllOrders(final CryptoSymbol symbol, final List<Purchase> orders) {
		BigDecimal result = BigDecimal.ZERO;
		for (Purchase order : orders) {
			if (order.getSymbol() == symbol) {
				result = result.add(order.getTotalFee());
			}
		}
		return result;
	}

}
