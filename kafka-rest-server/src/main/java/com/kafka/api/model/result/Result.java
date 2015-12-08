package com.kafka.api.model.result;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * Created by Nagendra on 12/8/15.
 */

@XmlRootElement(name = "result")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Result<T> implements Serializable {

    @XmlElement(name = "data")
    private Data<T> data;

    @XmlElement(name = "messages")
    private List<Message> messages = new LinkedList<Message>();

    @XmlAttribute
    private Code code = Code.SUCCESS;

    /**
     * Only for marshaling don't use.
     */
    public Result() {
    }

    /**
     * Constructor
     *
     * @param data the data of the result
     * @param code the type of the result
     */
    private Result(final Data<T> data, final Code code) {
        this.data = data;
        this.code = code;
    }

    /**
     * Create a Result instance with a (pre-built) Data&lt;T&gt; payload.
     *
     * @param data     result payload.
     * @param messages (optional) list of messages.
     * @param <T>      The dataType of the result
     * @return The constructed result
     */
    public static <T> Result<T> result(final Data<T> data, final Message... messages) {
        return result(data, newArrayList(messages));
    }

    public static <T> Result<T> result(final Data<T> data, final Collection<Message> messages) {
        Result<T> result = new Result<T>(data, Code.SUCCESS);
        result.addMessages(messages);
        return result;
    }

    /**
     * Adds messages, to any messages already set for the Result (appending to old)
     *
     * @param messages collection of messages
     */
    public Result<T> addMessages(final Collection<Message> messages) {
        this.messages.addAll(messages);
        return this;
    }

    /**
     * Wrap the payload object <code>data</code> within a Data object and create a new Result instance.
     *
     * @param data
     * @param messages
     * @param <T>
     * @return
     */
    public static <T> Result<T> resultOf(final T data, final Message... messages) {
        return result(Data.data(data), messages);
    }

    public static <T> Result<T> error(final Message... messages) {
        Result<T> result = new Result<T>(null,Code.ERROR);
        result.addMessages(messages);
        return result;
    }

    /**
     * Add message(s) (appending to previous)
     *
     * @param messages messages to add
     */
    public Result<T> addMessages(final Message... messages) {
        addMessages(asList(messages));
        return this;
    }

    /**
     * Create an error result from <code>cause</code>.
     *
     * @param throwable The throwable that the result wraps
     * @param <T>       The dataType of the result
     * @return an Error Result
     */
    public static <T> Result<T> error(final Throwable throwable) {
        return error(Message.error(throwable));
    }
}
