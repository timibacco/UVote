package votingme.core.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(StorageException.class)
    public ResponseEntity<?> storageExceptionHandler(StorageException ex){
        return ResponseEntity.badRequest().build();

    }
}
