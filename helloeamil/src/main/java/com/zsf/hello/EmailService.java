package com.zsf.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @program: helloeamil
 * @ClassName: EmailService
 * @description: emailservice
 * @author: 周生锋
 * @create: 2018-11-29 10:45
 **/
@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;


    /**
     * @Description: 发送简单邮件
     * @Param: [to  接收人, subject  主题, content  内容]
     * @return: void
     * @Author: 周生锋
     * @Date: 2018/11/29
     */
    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);

        mailSender.send(message);
    }

    /**
     * @Description: 发送html格式的邮件
     * @Param: [to, subject, content] 接收人 主题  内容
     * @return: void
     * @Author: ZhouShengfeng
     * @Date: 2018/11/29
     */
    public void sendHtmlEmail(String to, String subject, String content) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(from);
        mailSender.send(mimeMessage);
        

    }

    /**
     * @Description: 发送带附件的邮件
     * @Param: [to, subject, content, filePath] 文件路径
     * @return: void
     * @Author: ZhouShengfeng
     * @Date: 2018/11/29
     */
    public void sendAttachmentsEmail(String to, String subject, String content, String filePath) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);

        FileSystemResource resource = new FileSystemResource(new File(filePath));
        String fileName = resource.getFilename();
        helper.addAttachment(fileName, resource);
        mailSender.send(mimeMessage);

    }

    /**
     * @Description:发送图片格式的邮件
     * @Param: [to, subject, content, rscPath, rscId]
     * @return: void
     * @Author: ZhouShengfeng
     * @Date: 2018/11/29
     */
    public void sendInlinResourceMail(String to, String subject, String content, String rscPath, String rscId) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource resource = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId, resource);
        mailSender.send(mimeMessage);

    }

}
