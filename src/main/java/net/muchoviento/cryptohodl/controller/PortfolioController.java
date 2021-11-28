package net.muchoviento.cryptohodl.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import net.muchoviento.cryptohodl.dao.PurchaseDao;
import net.muchoviento.cryptohodl.model.PortfolioSummary;
import net.muchoviento.cryptohodl.model.Purchase;
import net.muchoviento.cryptohodl.service.CryptoPortfolioService;

import org.springframework.ui.Model;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;

@Controller
public class PortfolioController {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseController.class);

    @Resource
	private CryptoPortfolioService cryptoPortfolioService;

    private PurchaseDao purchaseDao = new PurchaseDao();

    @GetMapping("/")
	public String getRoot(Model model) {
		LOG.info("getRoot()");
		return "redirect:portfolio";
	}

    @GetMapping("/portfolio")
	public String getPortfolio(Model model) {
		LOG.info("getPortfolio()");
        List<Purchase> purchases = purchaseDao.loadPurchases();
		PortfolioSummary summary;
        try {
            summary = cryptoPortfolioService.generateSummary(purchases);
        } catch (Exception e) {
            LOG.error("error", e);
            return "portfolio";
        }
		LOG.info(summary.toString());
        model.addAttribute("currentValue", summary.getCurrentValue());
        model.addAttribute("currentInvestment", summary.getCurrentInvestment());
        model.addAttribute("items", summary.getItems());
		return "portfolio";
	}
    
}
