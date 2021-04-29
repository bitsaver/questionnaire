package pers.yang.questionnaire.utils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Log
@Component
public class EmailUtil {

	@Value("${spring.mail.username}")
	private String from;


	@Resource
	JavaMailSender javaMailSender;

	//参考https://docs.spring.io/spring-framework/docs/5.3.6/reference/html/integration.html#mail-javamail-mime
	public Boolean send(String to,String subject,String text) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text,true);

		javaMailSender.send(mimeMessage);

		return true;
	}
}
