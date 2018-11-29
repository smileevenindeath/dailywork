package com.zsf.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


/**
 * @Description:
 * @Author: 周生锋
 * @Date: 2018/11/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Autowired
    private TemplateEngine templateEngine; // 解析模板使用

    @Test
    public void sendSimpleEmail() {
        emailService.sendSimpleEmail("1430290569@qq.com", "springboot-mail", "DEMO-1");

    }

    @Test
    public void sendHtmlEmail() throws Exception {

        String content = "<html>\n" +
                "<body>\n" +
                "<h1 color='red'>hello word <h1>\n" +
                "</body>\n" +
                "</html>";
        emailService.sendHtmlEmail("1430290569@qq.com", "springboot-mail-html", content);
    }

    @Test
    public void sendAttachmentsEmail() throws Exception {
        String path = "D:/QRCode.png";

        emailService.sendAttachmentsEmail("1430290569@qq.com", "springboot-mail-Attachments", "附件", path);
    }

    @Test
    public void sendInlinResourceMail() throws Exception {
        String path = "C:\\Users\\周生锋\\Pictures\\20160811202433_iG82Q.jpeg";
        String id = "ceshi";
        String content = "<html>\n" +
                "<body>\n" +
                "<h1>Image:<h1><img src=\'cid:" + id + "\'></img>\n" +
                "</body>\n" +
                "</html>";
        emailService.sendInlinResourceMail("zhou_shengfeng@163.com", "springboot-mail-Image", content, path, id);
    }

    @Test
    public void testTemplateEmail() throws Exception {
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("EmailTemplate", context);

        emailService.sendHtmlEmail("1430290569@qq.com", "springboot-mail-html-template", emailContent);
    }
}