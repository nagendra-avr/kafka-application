package com.kafka.api.model.result;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Created by Nagendra on 12/8/15.
 * Indicates error,info,success type conditions.
 */

@XmlEnum
public enum Code {

    @XmlEnumValue("ERROR")ERROR(true),
    @XmlEnumValue("INFO")INFO,
    @XmlEnumValue("SUCCESS")SUCCESS;

    private final boolean code;

    Code(boolean code) {
        this.code = code;

    }

    Code() {
        this.code = false;
    }

    /**
     * @return {@value true} if the Result is an error result, {@value false} otherwise
     */
    public boolean isError() {
        return code;
    }
}

