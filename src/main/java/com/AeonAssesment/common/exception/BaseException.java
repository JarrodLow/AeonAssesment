package com.AeonAssesment.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class BaseException extends RuntimeException{
    protected String msg;

    protected String exId;

    protected Object[] params;

    protected List<BaseException> baseExceptionList;

    protected Throwable wrpEx;

    public BaseException() {
        super();
        gnrExUid();
    }

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
        gnrExUid();
    }

    public BaseException(String msg, Object[] params) {
        super(msg);
        this.params = params;
        gnrExUid();
    }

    public BaseException(Throwable ex) {
        super(ex);
        this.wrpEx = ex;
        gnrExUid();
    }

    public BaseException(String msg, Throwable ex) {
        this(msg);
        this.wrpEx = ex;
        gnrExUid();
    }

    public BaseException(String msg, Object[] params, Throwable ex) {
        this(msg, params);
        this.params = params;
        this.wrpEx = ex;
        gnrExUid();
    }

    @Override
    public synchronized Throwable getCause() {
        return this.wrpEx;
    }

    public String toString() {
        StringBuilder exBuilder = new StringBuilder();

        exBuilder.append("BaseException { ").append("Message=").append(msg).append(" Exception ID=").append(exId)
                .append(" Parameters=").append(Arrays.toString(params)).append(" Nested Exceptions=").append(baseExceptionList)
                .append(" Wrapped Exception=").append(wrpEx).append(" }");

        return exBuilder.toString();
    }

    private void gnrExUid() {
        UUID uid = UUID.randomUUID();

        int hashUid = uid.toString().hashCode();
        this.exId = String.valueOf(hashUid).replace("-", "");
    }
}
