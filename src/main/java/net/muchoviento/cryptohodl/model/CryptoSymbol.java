package net.muchoviento.cryptohodl.model;

public enum CryptoSymbol {
	
	ICX("Icon Project", "https://icon.foundation", null),
	
	ALGO("Algorand","https://algorand.foundation", 1_000_000_000_000L), // 1 Billion

	XRP("Ripple","https://ripple.com", 100_000_000_000L),
	
	ETH("Ethereum", "https://ethereum.org", null),
	
	ETC("Ethereum Classic", "https://www.ethereumclassic.org", 210_700_000L),
	
	BTC("Bitcoin", "https://bitcoin.org", 21_000_000L),
	
	DASH("Dash", "https://www.dash.org", 18_900_000L), // 18,9 Million
	
	LTC("Litecoin", "https://litecoin.com", 84_000_000L), // 84 Million
	
	NANO("Nano", "https://nano.org", 133_248_297L),
	
	QTUM("Qtum","https://qtum.org", 107_822_406L),
	
	ZEC("Zcash", "https://z.cash", 21_000_000L),

	DOT("Polkadot", "https://polkadot.network", null),

	DOGE("Dogecoin", "https://dogecoin.com", null),

	KSM("Kusama", "https://kusama.network", null),

	SC("Siacoin", "https://sia.tech/", null),
	
	ADA("Cardano", "https://cardano.org", 45_000_000_000L);
	
	public final String name;
	
	public final String websiteUrl;
	
	public final Long maxSupply;	
	
	private CryptoSymbol(String name, String websiteUrl, Long maxSupply) {
		this.name = name;
		this.websiteUrl = websiteUrl;
		this.maxSupply = maxSupply;
	}

	public String getName() {
        return name;
    }

}
