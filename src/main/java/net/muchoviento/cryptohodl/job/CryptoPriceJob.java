package net.muchoviento.cryptohodl.job;

import net.muchoviento.cryptohodl.controller.SmtpDto;
import net.muchoviento.cryptohodl.dao.PurchaseDao;
import net.muchoviento.cryptohodl.dao.SmtpDao;
import net.muchoviento.cryptohodl.mail.MailService;
import net.muchoviento.cryptohodl.model.PortfolioSummary;
import net.muchoviento.cryptohodl.service.CryptoPortfolioService;
import net.muchoviento.cryptohodl.model.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

// see: https://spring.io/guides/gs/scheduling-tasks/
@Service
public class CryptoPriceJob {
	
	private static final Logger LOG = LoggerFactory.getLogger(CryptoPriceJob.class);
	
	private static final String EVERY_MINUTE = "0 * * * * *";
	
	private static final String EVERY_HOUR = "0 0 * * * *";

	private static final String EVERY_DAY_AT_6 = "0 0 6 * * *";

	@Resource
	private CryptoPortfolioService cryptoPortfolioService;

	@Resource
	private MailService mailService;

	private PurchaseDao purchaseDao = new PurchaseDao();

	private SmtpDao smtpDao = new SmtpDao();
	
	@Scheduled(cron = EVERY_MINUTE)
	public void dummyActionEveryMinute() throws Exception {
		LOG.info("This job executes every minute...");
	}

	@Scheduled(cron = EVERY_HOUR)
	public void hourlyDummyAction() {
		LOG.info("This job executes every hour...");
	}

	@Scheduled(cron = EVERY_DAY_AT_6)
	public void mailAtSix() throws Exception {
		LOG.info("This job executes every day at six...");

		List<Purchase> purchases = purchaseDao.loadPurchases();
		PortfolioSummary summary = cryptoPortfolioService.generateSummary(purchases);
		LOG.info(summary.toString());

		SmtpDto smtpDto = smtpDao.loadSmtp();
		mailService.sendReport(smtpDto, "Your Cryptos", smtpDto.getMailTo(), summary.toString());
	}

}
