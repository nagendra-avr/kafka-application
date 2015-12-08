package com.kafka.api.model.result;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Nagendra on 12/8/15.
 */
@XmlType(name = "data", propOrder = {"data"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Data<T> implements Serializable {

    @XmlElement(name = "data")
    private  T data;

    /**
     * Constructor required for un-marshaling
     */
    public Data() {

    }

    /**
     * Constroctor
     *
     * @param data payload of wrapper
     */
    private Data(final T data) {
        this.data = data;
    }

    /**
     * Wrap an object in a Data wrapper.
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> Data<T> data(final T object) {
        Data<T> result = new Data<T>(object);
        return result;
    }

    /**
     * Get the wrapped object.
     *
     * @return the payload
     */
    public T getData() {
        return data;
    }

    /**
     * Set the wrapped object.
     *
     * @param data the payload
     */
    public void setData(T data) {
        this.data = data;
    }
}
