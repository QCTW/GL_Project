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
 *  This is an Email Alert System
 *  - If a task was not assigned to any MRO and the start date is soon (controlled with limit_not_assigned)
 *    then send an email to the MCC
 *  - If a task was assigned but not confirmed by the MRO and the task start date is soon
 *    (Controlled with limit_not_confirmed), then send an email to the MCC
 *
 *  debug_email : is an address email for debugging: each email sent by our system will be sent to this email to.
 *
 *  For the purpose od this ALERT SYSTEM, we created an email address to send email
 *  email: no.reply.alert.task@gmail.com
 *  password: 123456789ABC
 */
public class EmailAlertService
{
    static int limit_not_assigned = 400; // 400 days (for testing purpose)
    static int limit_not_confirmed = 400; // 400 days (for testing purpose)
    static String debug_email = "zidane.rezzak@gmail.com";

    static List<String> idtask_list_not_assigned = new ArrayList<String>();
    static List<String> idtask_list_not_confirmed = new ArrayList<String>();
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void main(String[] args) throws Exception {
        send_mail_to_MRO("6");
    }

    public static void send_mail_to_MRO(String taskId) throws AddressException, MessagingException {

        // fix countDay
        int countDay = 99999;

        // Step0
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm");
        DateTime today = new DateTime(); // today date and time

        DatabaseConnecter db = new DatabaseConnecter();
        List<Map<String, String>> list =  db.selectAllFromTableWhereFieldEqValue("task", "_id", taskId);

        if (list.get(0).get("mroId").equals("-1")) {
            System.out.println("mroId of this task is -1, no email sent.");
            db.close();
            return;
        }

        if (!list.get(0).get("taskStatus").equals("2")) {
            System.out.println("the task status is not 2, no email sent.");
            db.close();
            return;
        }

        DateTime startTime = formatter.parseDateTime(list.get(0).get("startTime"));
        int days = Days.daysBetween(today, startTime).getDays();

        if (days <= countDay) {
            String emailBody = "System Alert. " + "<br><br> You have been assigned a new task (N°" + taskId +
                    ") " + " but was not yet confirmed by you.<br> Task Start time: " + startTime;

            List<Map<String, String>> list2 =  db.selectAllFromTableWhereFieldEqValue("mro", "_id", list.get(0).get("mroId"));
            
            //String mail = list2.get(0).get("email");
            String mail = "mohameddiallo93md@gmail.com";
            
            send_mail(emailBody, mail, "\"ALERT: task not confirmed yet");
        }

        db.close();
    }

    public static void iterateAllTaskAndsendEmailtoMCC(String args[]) throws AddressException, MessagingException {
        generateAndSendEmail();
        System.out.println("\n\n ===> Emails sent successfully. Check your emails..");
    }

    private static void generateAndSendEmail() throws AddressException, MessagingException {

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

    private static void send_mail_to_mcc_task_not_confirmed(String id, String mccId, String mroId, String date) throws AddressException, MessagingException{
        String emailBody = "System Alert. " + "<br><br> We have detected that the task N°" + id +
                " " + "was assigned to mro N°" + mroId +", but was not yet confirmed by this one.<br> Task Start time: " + date + "";

        DatabaseConnecter db = new DatabaseConnecter();
        List<Map<String, String>> list = db.selectAllFromTableWhereFieldEqValue("mcc", "_id", mccId);
        db.close();

        send_mail(emailBody, list.get(0).get("email"), "\"ALERT: task not confirmed");
    }

    private static void send_mail_to_mcc_task_not_assigned(String id, String mccId, String date) throws AddressException, MessagingException {
        String emailBody = "System Alert. " + "<br><br> We have detected that the task N°" + id +
                " " + "was not assigned to anyone.<br> Task Start time: " + date + "";

        DatabaseConnecter db = new DatabaseConnecter();
        List<Map<String, String>> list = db.selectAllFromTableWhereFieldEqValue("mcc", "_id", mccId);
        db.close();

        send_mail(emailBody, list.get(0).get("email"), "\"ALERT : task not assigned\"");

        //System.out.println("taskId: " + id + "\tmccID: " + mccId + "\tmcc_email: " + list.get(0).get("email"));


    }

    private static void send_mail(String email_body, String email_address, String title) throws AddressException, MessagingException {
        // Step1
        //System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        //System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        //System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email_address));
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(debug_email));
        generateMailMessage.setSubject(title);
        generateMailMessage.setContent(email_body, "text/html");
        //System.out.println("Mail Session has been created successfully..");

        // Step3
        //System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", "no.reply.alert.task@gmail.com", "123456789ABC");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
        System.out.println("Mail have been sent to: " + email_address);

    }
}
