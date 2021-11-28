package net.muchoviento.cryptohodl.controller;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.muchoviento.cryptohodl.dao.SmtpDao;
import net.muchoviento.cryptohodl.mail.MailService;

@Controller
public class SmtpController {

    private static final Logger LOG = LoggerFactory.getLogger(SmtpController.class);

	@Resource
	private MailService mailService;

    private SmtpDao smtpDao = new SmtpDao();
    
    @GetMapping("/smtp")
	public String getSmtpSettings(Model model) {
		LOG.info("getSmtpSettings()");
        final SmtpDto smtpDto = smtpDao.loadSmtp();
        model.addAttribute("smtpDto", smtpDto); // for form initialization
		return "smtp";
	}

	@PostMapping(value = "/saveSmtp")
	public String saveSmtp(@ModelAttribute SmtpDto smtpDto, BindingResult errors, Model model) {
		LOG.info("saving smtp settings: " + smtpDto.getServer());
        smtpDao.saveSmtp(smtpDto);
		return "redirect:portfolio";
	}

	@GetMapping(value = "/testSmtp")
	public String testSmtp() throws MessagingException {
		LOG.info("test smtp...");
		SmtpDto smtpDto = smtpDao.loadSmtp();
		mailService.sendReport(smtpDto, "Test mail from Crypto-HODL", smtpDto.getMailTo(), "Your smtp setting works");
		return "redirect:portfolio";
	}

}
