package mx.com.invested.crud.exception;

import java.util.Date;

/**
 * 
 * @author Alexis Novella Vidal
 *
 */
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    /**
     * Constructor
     * @param timestamp
     * @param message
     * @param details
     */
    public ErrorDetails(Date timestamp, String message, String details) {
         super();
         this.timestamp = timestamp;
         this.message = message;
         this.details = details;
    }

    public Date getTimestamp() {
         return timestamp;
    }

    public String getMessage() {
         return message;
    }

    public String getDetails() {
         return details;
    }
}