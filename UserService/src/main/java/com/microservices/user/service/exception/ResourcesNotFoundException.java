package com.microservices.user.service.exception;

public class ResourcesNotFoundException extends RuntimeException{
    public ResourcesNotFoundException(){
        super("Resource Not Found On Server!");
    }
    public ResourcesNotFoundException(String message){
        super(message);
    }


}
