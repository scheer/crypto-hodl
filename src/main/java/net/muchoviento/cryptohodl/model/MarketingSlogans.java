package net.muchoviento.cryptohodl.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketingSlogans {
	
	private MarketingSlogans() {
		// util-class
	}
	
	public static Map<CryptoSymbol, List<String>> SLOGANS = new HashMap<>();
	
	static {

		SLOGANS.put(CryptoSymbol.ALGO, Arrays.asList("Building the Borderless Economy",
				"Designed by Silvio Micali and a team of leading scientists",
				"Advanced Technology Paving the Way for Sophisticated Applications"));
		
		SLOGANS.put(CryptoSymbol.ICX, Arrays.asList("CONNECTING CRYPTO TO REAL WORLD"));
		
		SLOGANS.put(CryptoSymbol.ETH, Arrays.asList("A fairer financial system",
				"Ethereum is open to everyone",
				"Censorship-resistant",
				"It's the world's programmable blockchain."));
		
		SLOGANS.put(CryptoSymbol.ETC, Arrays.asList("Decentralized - Immutable - Unstoppable"));
		
		SLOGANS.put(CryptoSymbol.BTC, Arrays.asList("An innovative payment network and a new kind of money"));
		
		SLOGANS.put(CryptoSymbol.DASH, Arrays.asList("Your money, your way",
				"Digital money that’s better than cash",
				"Dash is instant, global, and easy to use"));	
		
		SLOGANS.put(CryptoSymbol.LTC, Arrays.asList("THE FUTURE OF MONEY"));
		
		SLOGANS.put(CryptoSymbol.NANO, Arrays.asList("Digital money for the modern world",
				"Nano makes money efficient for a more equal world",
				"Simple to pay with, easy to accept and open to all"));
		
		SLOGANS.put(CryptoSymbol.QTUM, Arrays.asList("Blockchain Ready for Business"));
		
		SLOGANS.put(CryptoSymbol.ZEC, Arrays.asList("Zcash is digital money ",
				"Fast and confidential with low fees",
				"Built for Internet and mobile transactions",
				"Built on rigorous science"));
		
		SLOGANS.put(CryptoSymbol.ADA, Arrays.asList("Making the world work better for all",
				"A history of impossible, made possible",
				"The most environmentally sustainable blockchain protocol"));

		SLOGANS.put(CryptoSymbol.XRP, Arrays.asList("Enabling the Internet of Value",
				"A New Era of Finance"));
	}

}
