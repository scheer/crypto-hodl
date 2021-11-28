package net.muchoviento.cryptohodl.dao;

import net.muchoviento.cryptohodl.model.CryptoSymbol;
import net.muchoviento.cryptohodl.model.Purchase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public final class PurchaseJsonMapper {

    private PurchaseJsonMapper() {
        // util-class
    }

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseJsonMapper.class);

    private static final String DATE_TIME = "dateTime";

    private static final String SYMBOL = "symbol";

    private static final String AMOUNT = "amount";

    private static final String TOTAL_FEE = "totalFee";

    private static final String UUID = "uuid";
    
    public static String toJSON(final List<Purchase> purchases) {
        final JSONWriter jsonStringer = new JSONStringer().array();
        for (Purchase purchase : purchases) {
            jsonStringer.object()
            .key(UUID).value(purchase.getUuid())
            .key(DATE_TIME).value(purchase.getDateTime().toOffsetDateTime().toString())
            .key(SYMBOL).value(purchase.getSymbol().toString())
            .key(AMOUNT).value(purchase.getAmount())
            .key(TOTAL_FEE).value(purchase.getTotalFee()).endObject();
        }
        return jsonStringer.endArray().toString();
    }

    public static List<Purchase> toPurchases(final String asJson) {
        final JSONArray jsonArray = new JSONArray(asJson);
        final List<Purchase> result = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            LOG.debug(jsonObject.toString());
            final String dateTimeString = jsonObject.getString(DATE_TIME);
            final ZonedDateTime dateTime = ZonedDateTime.parse(dateTimeString).withZoneSameLocal(ZoneId.systemDefault());
            Purchase purchase = new Purchase(dateTime,
                    CryptoSymbol.valueOf(jsonObject.getString(SYMBOL)),
                    jsonObject.getBigDecimal(AMOUNT),
                    jsonObject.getBigDecimal(TOTAL_FEE),
                    jsonObject.getString(UUID));
            result.add(purchase);
        }
        return result;
    }

}
