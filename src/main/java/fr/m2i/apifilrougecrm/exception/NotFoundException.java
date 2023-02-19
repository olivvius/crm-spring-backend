package fr.m2i.apifilrougecrm.exception;

public class NotFoundException extends RuntimeException {
    
    public NotFoundException() {
        super("Resource was not found");
    }
}
