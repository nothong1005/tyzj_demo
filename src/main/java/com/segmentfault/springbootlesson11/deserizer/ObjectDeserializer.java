package com.segmentfault.springbootlesson11.deserizer;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.*;
import java.util.Map;

public class ObjectDeserializer implements Deserializer<Object> {


    @Override
    public Object deserialize(String topic, byte[] data) {
        Object object = null;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);

        try {
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);

            object = inputStream.readObject();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        System.out.println("反序列化---"+"topic:"+topic+", deserializer object:"+object);

        return object;
    }
}
