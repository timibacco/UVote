package votingme.core.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import votingme.core.exceptions.StorageException;
import votingme.core.service.StorageService;
import votingme.core.utils.Storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;



@Slf4j
@Service
public class FileStrorageService implements StorageService {

    private final Path rootLocation;


    @Autowired
    public FileStrorageService(Storage storageProperties){
        if (storageProperties.getDirectory().trim().isEmpty()){
            throw new StorageException("File upload location can not be Empty.");
        }

        this.rootLocation = Path.of(storageProperties.getDirectory());



    }

    @Override
    public void init() {



    }

    @Override
    public void store(MultipartFile file) {

        if (file.isEmpty()){
            throw new StorageException("File cannot be empty");


        }

       try { Path destinationFile = rootLocation.resolve(
                Path.of(file.getOriginalFilename())).normalize().toAbsolutePath();

        if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())){

            throw new StorageException("File cannot be stored outside directory");
        }

        try (
            InputStream input = file.getInputStream()){
                Files.copy(input, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (IOException ex){
            log.error("Error while saving a file:", ex);
            throw new StorageException("Failed to store file");
        }


    }

    @Override
    public Stream<Path> loadAll() {
        return Stream.empty();
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
