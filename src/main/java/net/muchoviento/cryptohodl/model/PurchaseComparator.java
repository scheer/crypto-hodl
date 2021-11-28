package net.muchoviento.cryptohodl.model;

import java.util.Comparator;

public class PurchaseComparator implements Comparator<Purchase> {

    @Override
    public int compare(Purchase o1, Purchase o2) {
        return o1.getDateTime().compareTo(o2.getDateTime());
    }
    
}
