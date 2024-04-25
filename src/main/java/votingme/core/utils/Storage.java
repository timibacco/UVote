package votingme.core.utils;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class Storage {


    /**
     * THIS WOULD BE OUR CLASS FOR STORAGE PROPERTIES
     */

    private String directory = "UPLOAD_DIR";


    public String getDirectory(){
        return directory;
    }

    public void setDirectory(String directory){
        this.directory = directory;
    }
}