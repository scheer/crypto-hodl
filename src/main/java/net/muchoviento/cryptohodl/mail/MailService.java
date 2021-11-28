package net.muchoviento.cryptohodl.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import net.muchoviento.cryptohodl.controller.SmtpDto;

/**
 * More Infos:<p>
 * http://openbook.galileocomputing.de/java7/1507_11_012.html#dodtp231b6739-4f26-466f-8db0-027753d32b40
 * 
 * @author michael
 *
 */
@Service
public class MailService {
	
	public void sendReport(final SmtpDto smtpDto,
			final String subject,
			final String mailTo,
			final String body) throws MessagingException {		
		
		final Session session = getSession(smtpDto);
		postMail(session, smtpDto, mailTo, subject, body);		
	}
	
	private Session getSession(final SmtpDto smtpDto) {
		final Properties props = new Properties();

		props.setProperty("mail.smtp.host", smtpDto.getServer());
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", smtpDto.getPort());
		props.setProperty("mail.smtp.socketFactory.port", smtpDto.getPort());
		props.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");

		return Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpDto.getUser(), smtpDto.getPwd());
			}
		});
	}

	private void postMail(final Session session,
			final SmtpDto smtpDto,
			final String mailTo,
			final String subject,
			final String body) throws MessagingException {

		final Message msg = new MimeMessage(session);

		InternetAddress addressTo = new InternetAddress(mailTo);
		msg.setRecipient(Message.RecipientType.TO, addressTo);
		msg.setFrom(new InternetAddress(smtpDto.getUser()));
		msg.setSubject(subject);

		MimeMultipart mimeMultipart = new MimeMultipart();
		msg.setContent(mimeMultipart);

		// text
		MimeBodyPart text = new MimeBodyPart();
		text.setText(body);
		text.setDisposition(MimeBodyPart.INLINE);
		mimeMultipart.addBodyPart(text);

		Transport.send(msg);
	}
	
}
