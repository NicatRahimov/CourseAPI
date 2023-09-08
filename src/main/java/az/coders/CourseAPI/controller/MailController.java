package az.coders.CourseAPI.controller;

import az.coders.CourseAPI.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    EmailService emailService;
    @PostMapping("send")
    public String sendEmail(
            @RequestParam String toEmail,
            @RequestParam String subject,
            @RequestParam String message
    ) {
        emailService.sendSimpleEmail(toEmail, subject, message);
        return "Email sent successfully!";
    }

}
