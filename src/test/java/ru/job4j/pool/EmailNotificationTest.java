package ru.job4j.pool;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class EmailNotificationTest {
    @Test
    public void test1() throws Exception {
        OutputStream outputStream = new ByteArrayOutputStream();
        try (EmailNotification email = new EmailNotification(new PrintStream(outputStream))) {
            User user1 = new User();
            User user2 = new User();
            User user3 = new User();
            User user4 = new User();
            User user5 = new User();
            User user6 = new User();
            User user7 = new User();
            User user8 = new User();
            User user9 = new User();
            user1.setEmail("user1@mail.ru");
            user1.setUsername("user1");
            user2.setEmail("user2@mail.ru");
            user2.setUsername("user2");
            user3.setEmail("user3@mail.ru");
            user3.setUsername("user3");
            user4.setEmail("user4@mail.ru");
            user4.setUsername("user4");
            user5.setEmail("user5@mail.ru");
            user5.setUsername("user5");
            user6.setEmail("user6@mail.ru");
            user6.setUsername("user6");
            user7.setEmail("user7@mail.ru");
            user7.setUsername("user7");
            user8.setEmail("user8@mail.ru");
            user8.setUsername("user8");
            user9.setEmail("user9@mail.ru");
            user9.setUsername("user9");
            email.emailTo(user1);
            email.emailTo(user2);
            email.emailTo(user3);
            email.emailTo(user4);
            email.emailTo(user5);
            email.emailTo(user6);
            email.emailTo(user7);
            email.emailTo(user8);
            email.emailTo(user9);
            Thread.sleep(1000);
            String res = outputStream.toString();
            assertTrue(res.contains(
                    "email:user1@mail.ru" + System.lineSeparator()
            + "subject:Notification user1 to email user1@mail.ru" + System.lineSeparator()
            + "body:Add a new event to user1" + System.lineSeparator()
            ));
            assertTrue(res.contains(
                    "email:user2@mail.ru" + System.lineSeparator()
                            + "subject:Notification user2 to email user2@mail.ru" + System.lineSeparator()
                            + "body:Add a new event to user2" + System.lineSeparator()
            ));
            assertTrue(res.contains(
                    "email:user3@mail.ru" + System.lineSeparator()
                            + "subject:Notification user3 to email user3@mail.ru" + System.lineSeparator()
                            + "body:Add a new event to user3" + System.lineSeparator()
            ));
            assertTrue(res.contains(
                    "email:user4@mail.ru" + System.lineSeparator()
                            + "subject:Notification user4 to email user4@mail.ru" + System.lineSeparator()
                            + "body:Add a new event to user4" + System.lineSeparator()
            ));
            assertTrue(res.contains(
                    "email:user5@mail.ru" + System.lineSeparator()
                            + "subject:Notification user5 to email user5@mail.ru" + System.lineSeparator()
                            + "body:Add a new event to user5" + System.lineSeparator()
            ));
            assertTrue(res.contains(
                    "email:user6@mail.ru" + System.lineSeparator()
                            + "subject:Notification user6 to email user6@mail.ru" + System.lineSeparator()
                            + "body:Add a new event to user6" + System.lineSeparator()
            ));
            assertTrue(res.contains(
                    "email:user7@mail.ru" + System.lineSeparator()
                            + "subject:Notification user7 to email user7@mail.ru" + System.lineSeparator()
                            + "body:Add a new event to user7" + System.lineSeparator()
            ));
            assertTrue(res.contains(
                    "email:user8@mail.ru" + System.lineSeparator()
                            + "subject:Notification user8 to email user8@mail.ru" + System.lineSeparator()
                            + "body:Add a new event to user8" + System.lineSeparator()
            ));
            assertTrue(res.contains(
                    "email:user9@mail.ru" + System.lineSeparator()
                            + "subject:Notification user9 to email user9@mail.ru" + System.lineSeparator()
                            + "body:Add a new event to user9" + System.lineSeparator()
            ));
        }
    }
}