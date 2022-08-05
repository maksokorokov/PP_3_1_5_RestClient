package org.example;

import org.example.configuration.MyConfig;
import org.example.models.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ){
        AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        communication.getAllUser();

        communication.saveUser(new User(3L, "James", "Brown", (byte) 11));

        User newUser = new User(3L, "Thomas", "Shelby", (byte) 11);
        communication.updateUser(newUser);

        communication.deleteUser(3);
    }
}
