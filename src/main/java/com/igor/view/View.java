package com.igor.view;

import com.igor.model.Stranger;
import com.igor.model.StrangerType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class View {
    public static void view() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Stranger hippie = (Stranger) context.getBean("stranger");
        hippie.talk();
        hippie.isCreated();
        Stranger good = new Stranger(StrangerType.GOOD, "I'm good");
        good.talk();
    }
}
