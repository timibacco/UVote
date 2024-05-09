package votingme.core.exceptions;


public class StorageFileNotFoundException extends StorageException{




    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message,  cause);

    }

    public StorageFileNotFoundException(String message){
        super(message);
    }
}
