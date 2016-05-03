package com.yzy.myaccount.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//导入正确的jar包
public class MailUtil {
    public static void snedMail(String subject, String content)
            throws MessagingException {
//      Properties props = new Properties();
//      props.setProperty("mail.transport.protocol", "smtp");
//      props.setProperty("mail.host", "smtp.sina.com");
//      props.put("mail.smtp.auth", "true");
//      props.put("mail.smtp.port", "465");
//      props.put("mail.smtp.socketFactory.port", "465");
//      props.put("mail.smtp.socketFactory.class",
//              "javax.net.ssl.SSLSocketFactory");
//      props.put("mail.smtp.socketFactory.fallback", "false");
//      props.setProperty("mail.smtp.quitwait", "false");
  
    	 Properties props = new Properties(); 
        props.put("mail.smtp.ssl.enable", true);
         props.put("mail.smtp.host", "smtp.163.com");// 存储发送邮件服务器的信息   
         props.put("mail.smtp.auth", "true");// 同时通过验证 
         // 创建邮件会话
         Session session = Session.getInstance(props);
         //session.setDebug(true);
   
         // 邮件体
         MimeMessage message = new MimeMessage(session);
   
         // 发邮件
         InternetAddress fromAddress = null;
         fromAddress = new InternetAddress("yangyi199311@163.com");
   
         message.setFrom(fromAddress);
   
         // 收件地址
         InternetAddress toAddress = new InternetAddress("yangyi199311@163.com");
         message.addRecipient(Message.RecipientType.TO, toAddress);
   
         // 解析邮件内容
         message.setSubject(subject);// 设置信件的标题
         message.setText(content);// 设置信件内容
         message.saveChanges(); //存储信息
   
         // send message
         Transport transport = null;
         transport = session.getTransport("smtp");
         transport.connect("smtp.163.com", "yangyi199311@163.com", "Yang16048409");
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
   
     }
}

