package net.muchoviento.cryptohodl.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public class Purchase implements Serializable {

	public Purchase(ZonedDateTime dateTime, CryptoSymbol symbol, BigDecimal amount, BigDecimal totalFee, final String uuid) {		
		super();
		this.dateTime = dateTime;
		this.symbol = symbol;
		this.amount = amount;
		this.totalFee = totalFee;
		this.uuid = uuid;
	}

	public Purchase(ZonedDateTime dateTime, CryptoSymbol symbol, BigDecimal amount, BigDecimal totalFee) {		
		super();
		this.dateTime = dateTime;
		this.symbol = symbol;
		this.amount = amount;
		this.totalFee = totalFee;
		this.uuid = UUID.randomUUID().toString();
	}

	private final ZonedDateTime dateTime;
	
	private final CryptoSymbol symbol;
	
	private final BigDecimal amount;
	
	private final BigDecimal totalFee;

	private final String uuid;
	
	public ZonedDateTime getDateTime() {
		return dateTime;
	}
	
	public CryptoSymbol getSymbol() {
		return symbol;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public String toString() {
		return "Purchase{" +
				"dateTime=" + dateTime +
				", symbol=" + symbol +
				", amount=" + amount +
				", totalFee=" + totalFee +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Purchase purchase = (Purchase) o;
		return uuid.equals(purchase.uuid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}
}
