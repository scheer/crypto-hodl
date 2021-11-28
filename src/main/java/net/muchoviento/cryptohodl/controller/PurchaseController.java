package net.muchoviento.cryptohodl.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.muchoviento.cryptohodl.dao.PurchaseDao;
import net.muchoviento.cryptohodl.model.Purchase;
import net.muchoviento.cryptohodl.model.PurchaseComparator;

@Controller
public class PurchaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseController.class);

	private PurchaseDao purchaseDao = new PurchaseDao();

	private List<Purchase> purchases;
    
    @GetMapping("/purchases")
	public String getPurchases(Model model) {
		LOG.info("getPurchases()");
		purchases = purchaseDao.loadPurchases();
		Collections.sort(purchases, new PurchaseComparator());
		model.addAttribute("purchases", purchases);
		model.addAttribute("purchaseDto", new PurchaseDto()); // for form initialization
		return "purchases";
	}

	@PostMapping(value = "/savePurchase")
	public String savePurchase(@ModelAttribute PurchaseDto purchaseDto, BindingResult errors, Model model) {
		LOG.info("saving new purchase: " + purchaseDto);
		LocalDate localDate = LocalDate.parse(purchaseDto.getDateTime());
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, ZoneId.systemDefault());
		Purchase purchase = new Purchase(zonedDateTime, purchaseDto.getSymbol(), purchaseDto.getAmount(), purchaseDto.getTotalFee());
		purchases.add(purchase);
		purchaseDao.savePurchasesAsJSON(purchases);
		model.addAttribute("purchases", purchases);
		return "redirect:purchases";
	}

	@RequestMapping(value = "/deletePurchase", method = RequestMethod.GET)
	public String deletePurchase(@RequestParam(name="uuid") String uuid) {
		LOG.info("deleting purchase with uuid: " + uuid);
		for (Iterator<Purchase> iterator = purchases.iterator(); iterator.hasNext();) {
			Purchase purchase = iterator.next();
			if (purchase.getUuid().equals(uuid)) {
				iterator.remove();
			}
		}
		purchaseDao.savePurchasesAsJSON(purchases);
		return "redirect:purchases";
	}

}
