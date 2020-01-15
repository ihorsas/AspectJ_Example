package com.igor.view;

import com.igor.model.Stranger;
import com.igor.reflection.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;

public class View {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    public static void showAspect() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Stranger hippie = (Stranger) context.getBean("stranger");
        hippie.talk();
        hippie.isCreated();
        separate();
    }

    public static void showReflection() {
        Reflection reflection = new Reflection();

        try {
            reflection.namesExample();
            separate();

            reflection.methodsExample();
            separate();

            reflection.invokeMethods();
            separate();

            reflection.manipulationWithFields();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    private static void separate(){
        logger.info("----------------------------------------------\n");
    }
}
