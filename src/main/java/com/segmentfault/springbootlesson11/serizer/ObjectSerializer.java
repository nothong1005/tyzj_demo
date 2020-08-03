package com.segmentfault.springbootlesson11.serizer;


import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;

public class ObjectSerializer implements Serializer<Object> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Object object) {

        System.out.println("序列化---"+"topics:" + topic + ",object:" + object);

        byte[] dataArray = null;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);

            dataArray = outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataArray;

    }


    @Override
    public void close() {

    }
}
