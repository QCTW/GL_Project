package com.example.jetty_jersey.Email;

/**
 * Created by abdel on 27/04/2017.
 */

import com.example.jetty_jersey.db.DatabaseConnecter;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * query the task database each sunday at 00:00 and send the emails
 * alpha version for now (not complete)
 *
 * For this purpose we created an email address for the group to send email
 *  email: no.reply.alert.task@gmail.com
 *  password: 123456789ABC
 */
public class EmailAlertService
{
    static int limit_not_assigned = 30;
    static int limit_not_confirmed = 2;
    static String debug_email = "zidane.rezzak@gmail.com";

    static List<String> idtask_list_not_assigned = new ArrayList<String>();
    static List<String> idtask_list_not_confirmed = new ArrayList<String>();
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void main(String args[]) throws AddressException, MessagingException {
        generateAndSendEmail();
        System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
    }

    public static void generateAndSendEmail() throws AddressException, MessagingException {

        // Step0
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm");
        DateTime today = new DateTime(); // today date and time
        //System.out.print(today);

        DatabaseConnecter db = new DatabaseConnecter();
        List<Map<String, String>> list =  db.selectAllFromTableName("task");

        for (Map<String, String> task : list) {
           //System.out.print(task.get("taskStatus"));
            if (task.get("taskStatus").equals("1")) { // if this  task is not dispatch to any MRO
                DateTime startTime = formatter.parseDateTime(task.get("startTime"));
                int days = Days.daysBetween(today, startTime).getDays();
                //System.out.println(days + " " + task.get("_id"));
                if (days <= limit_not_assigned && !idtask_list_not_assigned.contains(task.get("_id"))) {
                    //System.out.println(days + " " + task.get("_id"));
                    idtask_list_not_assigned.add(task.get("_id"));
                    send_mail_to_mcc_task_not_assigned(task.get("_id"), task.get("mccId"), task.get("startTime"));
                }
            }

            else if(task.get("taskStatus").equals("2")) { // if this task is not confirmed by the MRO
                DateTime startTime = formatter.parseDateTime(task.get("startTime"));
                int days = Days.daysBetween(today, startTime).getDays();
                //System.out.println(days + " " + task.get("_id"));
                if (days <= limit_not_confirmed && !idtask_list_not_confirmed.contains(task.get("_id"))) {
                    //System.out.println(days + " " + task.get("_id"));
                    idtask_list_not_confirmed.add(task.get("_id"));
                    send_mail_to_mcc_task_not_confirmed(task.get("_id"), task.get("mccId"), task.get("mroId"), task.get("startTime"));
                }
            }
        }
        db.close();
    }

    private static void send_mail_to_mcc_task_not_confirmed(String id, String mccId, String mroId, String date) {
    }

    private static void send_mail_to_mcc_task_not_assigned(String id, String mccId, String date) throws AddressException, MessagingException {
        String emailBody = "System Alert. " + "<br><br> We have detected that the task N°" + id +
                " " + "was not assigned to anyone.<br> Task Start time: " + date + "";

        DatabaseConnecter db = new DatabaseConnecter();
        List<Map<String, String>> list = db.selectAllFromTableWhereFieldEqValue("mcc", "_id", mccId);
        db.close();

        send_mail(emailBody, list.get(0).get("email"));

        //System.out.println("taskId: " + id + "\tmccID: " + mccId + "\tmcc_email: " + list.get(0).get("email"));


    }

    private static void send_mail(String email_body, String email_address) throws AddressException, MessagingException {
        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email_address));
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(debug_email));
        generateMailMessage.setSubject("ALERT task");
        generateMailMessage.setContent(email_body, "text/html");
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", "no.reply.alert.task@gmail.com", "123456789ABC");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();

    }
}
