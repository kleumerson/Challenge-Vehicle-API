package com.ags.backend.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {
    private String message;
    public MessageResponse(String message) {
        super();
        this.message = message;
    }
}
