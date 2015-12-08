package com.kafka.api.model.result;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Created by Nagendra on 12/8/15.
 */

@XmlType(name = "message") @XmlAccessorType(XmlAccessType.FIELD)
public class Message implements Serializable {



    private Code code;

    private String body;


    /**
     * Constructor
     *
     * @param code MessageCode
     * @param body MessageTextBody
     */
    public static Message message(final Code code, final String body) {
        Message msg = new Message();
        msg.code = code;
        msg.body = body;
        return msg;
    }


    /**
     * Create an error message from the <code>cause</code>'s message.
     *
     * @param cause root cause of ErrorMessage being generated
     * @return error message with the associated cause
     */
    public static Message error(final Throwable cause) {
        return error(cause.getMessage());
    }

    /**
     * Create an error message with a body of <code>body</code>.
     *
     * @param body text body of error message
     * @return the generated error message
     */
    public static Message error(final String body) {
        return message(Code.ERROR, body);
    }

    /**
     * Create an info message.
     *
     * @param body text associated with message
     * @return Message, with specified text
     */
    public static Message info(final String body) {
        return message(Code.INFO, body);
    }

    /**
     * Bet the type of Result this is (error,success.....)
     *
     * @return The message Code
     */
    public Code getCode() {
        return code;
    }

    /**
     * Get string Contents of the Message
     *
     * @return Message Body
     */
    public String getBody() {
        return body;
    }

    /**
     * Set type of Message this is (error,success.....)
     *
     * @param code MessageCode
     */
    public void setCode(Code code) {
        this.code = code;
    }

    /**
     * Set string Contents of the Message
     *
     * @return message Body
     */
    public void setBody(String body) {
        this.body = body;
    }
}
