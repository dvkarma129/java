package com.flightBookingSystem.service;

import java.util.Date;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.flightBookingSystem.dto.UsersDto;
import com.flightBookingSystem.repo.UserRepo;

@Service
public class EmailServiceImpl implements EmailService {

	private JavaMailSender javaMailSender;
	private final UserRepo urepo;

	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender, UserRepo urepo) {
		this.urepo = urepo;
		this.javaMailSender = javaMailSender;
	}

	@Override
	public String sendMail(UsersDto udto) {

		StringBuilder sb = new StringBuilder();

		sb.append("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Registration Successful</title>\r\n" + "    <style>\r\n" + "        body {\r\n"
				+ "            font-family: 'Arial', sans-serif;\r\n" + "            background-color: #f4f4f4;\r\n"
				+ "            margin: 0;\r\n" + "            padding: 0;\r\n" + "        }\r\n" + "\r\n"
				+ "        .container {\r\n" + "            width: 80%;\r\n" + "            max-width: 600px;\r\n"
				+ "            margin: 20px auto;\r\n" + "            background-color: #fff;\r\n"
				+ "            padding: 20px;\r\n" + "            border-radius: 5px;\r\n"
				+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + "        }\r\n" + "\r\n"
				+ "        h1 {\r\n" + "            color: #333;\r\n" + "            text-align: center;\r\n"
				+ "        }\r\n" + "\r\n" + "        p {\r\n" + "            color: #666;\r\n"
				+ "            line-height: 1.6;\r\n" + "        }\r\n" + "\r\n" + "        .button {\r\n"
				+ "            display: inline-block;\r\n" + "            padding: 10px 20px;\r\n"
				+ "            background-color: #3498db;\r\n" + "            color: #fff;\r\n"
				+ "            text-decoration: none;\r\n" + "            border-radius: 3px;\r\n"
				+ "            margin-top: 20px;\r\n" + "        }\r\n" + "\r\n" + "        .footer {\r\n"
				+ "            margin-top: 20px;\r\n" + "            text-align: center;\r\n"
				+ "            color: #999;\r\n" + "        }\r\n" + "    </style>\r\n" + "</head>\r\n" + "\r\n"
				+ "<body>\r\n" + "    <div class=\"container\">\r\n" + "        <h1>Registration Successful!</h1>\r\n"
				+ "        <p>Dear " + udto.getUserFullName() + ",\r\n" + "\r\n"
				+ "            Thank you  for registering on our flight booking website. <b>Your account has been successfully created</b>.</p>\r\n"
				+ "\r\n"
				+ "        <p>You can now explore and book flights, manage your reservations, and enjoy a seamless travel experience with our website.</p>\r\n"
				+ "\r\n"
				+ "        <p>Feel free to log in using your registered email and start planning your next journey!</p>\r\n"
				+ "\r\n" + "        <p class=\"footer\">Safe travels,<br> The FM&B Team</p>\r\n" + "    </div>\r\n"
				+ "</body>\r\n" + "\r\n" + "</html>\r\n" + "");
		try {

			MimeMessagePreparator preparator = new MimeMessagePreparator() {

				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
					messageHelper.setTo(udto.getUserGmail());
					messageHelper.setSentDate(new Date());
					messageHelper.setFrom(sender);
					messageHelper.setSubject("Registration");
					messageHelper.setText("sucessfully regeisterd");
					mimeMessage.setContent(sb.toString(), "text/html");
				}
			};

			this.javaMailSender.send(preparator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "User Successfully Register";
	}

	@Override
	public int sendOtp(UsersDto udto, int userId) {
		Random rnd = new Random();
		int otp = rnd.nextInt(999999);
		System.out.println("otp--->" + otp);

		StringBuilder sb = new StringBuilder();
//		sb.append(
//				"<html><head><title> login verifiaction </title></head><body><div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\"> <div style=\"margin:50px auto;width:70%;padding:20px 0\"><p style=\"font-size:1.1em\">Hi,</p> <p>Use the following OTP to complete your Sign-In</p> <h2 style=\"background: #36b5d4;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\"> "
//						+ otp
//						+ "</h2> <p style=\"font-size:0.9em;\">Regards,<br />Team FBS</p> </div> </div></body></html>\r\n");

		sb.append("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n" + "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Flight Booking OTP</title>\r\n" + "    <style>\r\n" + "        body {\r\n"
				+ "            font-family: 'Arial', sans-serif;\r\n" + "            background-color: #f4f4f4;\r\n"
				+ "            margin: 0;\r\n" + "            padding: 0;\r\n" + "        }\r\n" + "\r\n"
				+ "        .container {\r\n" + "            max-width: 600px;\r\n"
				+ "            margin: 20px auto;\r\n" + "            padding: 20px;\r\n"
				+ "            background-color: #fff;\r\n" + "            border-radius: 8px;\r\n"
				+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + "        }\r\n" + "\r\n"
				+ "        h1 {\r\n" + "            color: #333;\r\n" + "        }\r\n" + "\r\n" + "        p {\r\n"
				+ "            color: #555;\r\n" + "        }\r\n" + "\r\n" + "        .otp-container {\r\n"
				+ "            margin-top: 20px;\r\n" + "            padding: 15px;\r\n"
				+ "            background-color: #e0e0e0;\r\n" + "            border-radius: 4px;\r\n"
				+ "            font-size: 20px;\r\n" + "            font-weight: bold;\r\n"
				+ "            text-align: center;\r\n" + "        }\r\n" + "\r\n" + "        .note {\r\n"
				+ "            margin-top: 20px;\r\n" + "            color: #888;\r\n" + "        }\r\n" + "\r\n"
				+ "        .footer {\r\n" + "            margin-top: 20px;\r\n" + "            text-align: center;\r\n"
				+ "            color: #777;\r\n" + "        }\r\n" + "    </style>\r\n" + "</head>\r\n" + "<body>\r\n"
				+ "    <div class=\"container\">\r\n" + "        <h1>Flight Booking OTP</h1>\r\n" + "        <p>Hello "
				+ udto.getUserFullName() + ",</p>\r\n" + "        <p>Your One-Time Password (OTP) for login is:</p>\r\n"
				+ "        <div class=\"otp-container\">\r\n" + "            " + otp + "\r\n" + "        </div>\r\n"
				+ "        <p class=\"note\">This OTP is valid for a short period. Do not share it with anyone.</p>\r\n"
				+ "        <p class=\"footer\">Best regards,<br>Flight Booking Team</p>\r\n" + "    </div>\r\n"
				+ "</body>\r\n" + "</html>\r\n" + "");
		try {

			MimeMessagePreparator preparator = new MimeMessagePreparator() {

				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
					messageHelper.setTo(udto.getUserGmail());
					messageHelper.setSentDate(new Date());
					messageHelper.setFrom(sender);
					messageHelper.setSubject("Login verification");
					messageHelper.setText("Use 6 digit code to login");
					mimeMessage.setContent(sb.toString(), "text/html");
				}

			};
			udto.setUserOtp(otp);
			urepo.save(udto);
			this.javaMailSender.send(preparator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return otp;
	}

	public void sendTicketEmail(UsersDto udto, byte[] pdfData) {
	    try {
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {
	            @Override
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
	                messageHelper.setTo(udto.getUserGmail());
	                messageHelper.setSentDate(new Date());
	                messageHelper.setFrom(sender);
	                messageHelper.setSubject("Flight Booking Confirmation");

	                // Attach the PDF
	                messageHelper.addAttachment("ticket.pdf", new ByteArrayResource(pdfData), "application/pdf");

	                // Your existing email content
	                StringBuilder sb = new StringBuilder();
	                sb.append("<p>Dear " + udto.getUserFullName() + ",</p>");
	                sb.append("<p>Your flight booking is confirmed. Please find the attached ticket.</p>");
	                sb.append("<p>Safe travels!</p>");

	                messageHelper.setText(sb.toString(), true);
	            }
	        };

	        this.javaMailSender.send(preparator);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
}
