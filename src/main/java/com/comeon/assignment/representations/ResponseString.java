/**
 * 
 */
package com.comeon.assignment.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author prashant.pathania
 *
 */
public class ResponseString {
    String message;

    /**
     * Constructor with message parameter
     * @param message
     */
    public ResponseString(String message) {
        this.message = message;
    }

    /**
     * @return the message
     */
    @JsonProperty
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    
}
