package org.spring.boot.validator.dto;

import java.util.List;

/**
 * Created by Aleksey Stoyokha on 29.01.17.
 */
public class MessageDTO {
    private boolean success;
    private List<String> message;

    public MessageDTO() {

    }

    public MessageDTO(boolean success) {
        this.success = success;
    }

    public MessageDTO(boolean success, List<String> message) {
        this(success);
        this.message = message;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
