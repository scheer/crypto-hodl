package net.muchoviento.cryptohodl.dao;

import net.muchoviento.cryptohodl.model.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDao {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseDao.class);

    public void savePurchasesAsJSON(final List<Purchase> purchases) {
        final String asJSON = PurchaseJsonMapper.toJSON(purchases);
        LOG.info(asJSON);
        File purchaseFile = new File("./data/purchases.json");
        purchaseFile.getParentFile().mkdirs();
        byte[] strToBytes = asJSON.getBytes();
        try {
            Files.write(purchaseFile.toPath(), strToBytes);
        } catch (IOException e) {
            LOG.error("error", e);
        }
    }
    
    public List<Purchase> loadPurchases() {
        String json;
        try {
            json = Files.readString(Paths.get("./data/purchases.json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return new ArrayList<Purchase>();
        }
        return PurchaseJsonMapper.toPurchases(json);
    }

}
