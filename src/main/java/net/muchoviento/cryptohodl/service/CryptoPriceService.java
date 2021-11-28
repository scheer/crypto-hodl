package net.muchoviento.cryptohodl.service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.muchoviento.cryptohodl.model.CryptoSymbol;

@Service
public class CryptoPriceService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CryptoPriceService.class);

	private static final long ONE_HOUR = 1000 * 60 * 60;

	private static final Map<CryptoSymbol, BigDecimal> PRICE_CACHE = new HashMap<>();

	private static long cacheAgeInMs = 0;
	
	// see: https://www.baeldung.com/java-9-http-client
	// API documentation: https://min-api.cryptocompare.com/documentation
	public synchronized Map<CryptoSymbol, BigDecimal> actualPrices(final Collection<CryptoSymbol> symbols) throws Exception {
		
		final long currentTimeMillis = System.currentTimeMillis();
		if (currentTimeMillis > cacheAgeInMs + ONE_HOUR) {
			PRICE_CACHE.clear();
			cacheAgeInMs = currentTimeMillis;
		}

		final Map<CryptoSymbol, BigDecimal> result = new HashMap<>();
		final HttpClient client = HttpClient.newHttpClient();	
		for (CryptoSymbol symbol : symbols) {
			final BigDecimal price;
			if (PRICE_CACHE.containsKey(symbol)) {
				price = PRICE_CACHE.get(symbol);
			} else {
				final String uri = "https://min-api.cryptocompare.com/data/price?fsym=" + symbol.name() + "&tsyms=EUR";
			
				final HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(uri))
					.timeout(Duration.ofSeconds(10))			  
					.GET()
					.build();			
				final HttpResponse<?> response = client.send(request, BodyHandlers.ofString());
		
				LOG.info("Response: " + response);		
				LOG.info("Body: " + response.body());	
			
				price = parsePrice(response.body().toString());
				PRICE_CACHE.put(symbol, price);
			}
			result.put(symbol, price);
		}		
		return result;
	}

	// {"EUR":5.836}
	private BigDecimal parsePrice(final String responseBody) {
		final int beginIndex = responseBody.lastIndexOf('"');
		final String valueInEuro = responseBody.substring(beginIndex + 2, responseBody.length() - 1);
		LOG.info(valueInEuro);
		return new BigDecimal(valueInEuro);
	}

}
