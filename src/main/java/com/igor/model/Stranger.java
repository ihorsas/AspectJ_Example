package com.igor.model;

import com.sun.javafx.binding.StringFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Objects;

public class Stranger {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    private StrangerType type;
    private String speech;

    public Stranger(StrangerType type, String speech){
        this.type = type;
        this.speech = speech;
        logger.info(StringFormatter.format("Stranger was created with type: %s and speech: %s", type, speech).getValue());
    }

    public void talk(){
        logger.info(speech);
    }

    public boolean isCreated(){
        return !(Objects.isNull(type) && Objects.isNull(speech));
    }

    @Override
    public String toString() {
        return "Stranger{" +
                "type=" + type +
                ", speech='" + speech + '\'' +
                '}';
    }
}
