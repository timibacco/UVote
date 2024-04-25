package votingme.core.utils;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("storage")
public class Storage {


    /**
     * THIS WOULD BE OUR CLASS FOR STORAGE PROPERTIES
     */

    private String directory = "UPLOAD_DIR";


}