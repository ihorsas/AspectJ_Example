package com.igor.reflection;

import com.igor.model.Stranger;
import com.igor.model.StrangerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Reflection {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    public void namesExample() throws ClassNotFoundException {
        Class<?> strangerClass = Class.forName("com.igor.model.Stranger");
        logger.info("Simple name: " + strangerClass.getSimpleName());
        logger.info("Name: " + strangerClass.getName());
        logger.info("Canonical name: " + strangerClass.getCanonicalName());
        logger.info("Type name: " + strangerClass.getTypeName());
    }

    public void methodsExample() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> strangerClass;
        strangerClass = Class.forName("com.igor.model.Stranger");
        Method[] allMethods = strangerClass.getMethods();
        logger.info("All methods:");
        Arrays.stream(allMethods).forEach(method -> logger.info(method.getName()));

        Method[] declaredMethods = strangerClass.getDeclaredMethods();
        logger.info("Declared methods:");
        Arrays.stream(declaredMethods).forEach(method -> logger.info(method.getName()));

        Method isCreatedMethod = Arrays.stream(declaredMethods).filter(method1 -> method1.getName().equals("isCreated")).collect(Collectors.toList()).get(0);
        Method method = strangerClass.getMethod(isCreatedMethod.getName());
        logger.info("IsCreated methods: " + method.getName());
    }

    public void invokeMethods() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> strangerClass;
        strangerClass = Class.forName("com.igor.model.Stranger");
        Constructor constructor = strangerClass.getConstructor(new Class[]{StrangerType.class, String.class});
        logger.info("Invoking constructor");
        Stranger myObject = (Stranger)
                constructor.newInstance(StrangerType.WARRIOR, "I'll kill you, f*king bastard");

        Method publicMethod = strangerClass.getMethod("isCreated");
        logger.info("Result executing isCreated public Method: " + publicMethod.invoke(myObject));

        //PAY ATTENTION FOR EXECUTING PRIVATE METHOD YOU NEED USE getDeclaredMethod INSTEAD getMethod
        Method privateMethod = strangerClass.getDeclaredMethod("isAvatar");
        privateMethod.setAccessible(true);
        logger.info("Result executing isAvatar private Method: " + privateMethod.invoke(myObject));
    }

    public void manipulationWithFields() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        Class<?> strangerClass;
        strangerClass = Class.forName("com.igor.model.Stranger");
        Stranger myObject = new Stranger(StrangerType.GOOD, "I'm not as good as you think");
        Field field = strangerClass.getDeclaredField("type");
        field.setAccessible(true);
        logger.info("Initial value: " + field.get(myObject));
        logger.info("Changing field");
        field.set(myObject, StrangerType.WARRIOR);
        logger.info("Value after changing: " + field.get(myObject));
    }
}
