package votingme.core.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import votingme.core.exceptions.StorageException;
import votingme.core.exceptions.StorageFileNotFoundException;
import votingme.core.service.StorageService;
import votingme.core.utils.Storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;



@Slf4j
@Service
@RequiredArgsConstructor
public class FileStrorageService implements StorageService {


    private final Path rootLocation;


    @Autowired
    public FileStrorageService(Storage storageProperties) {
        if (storageProperties.getDirectory().trim().isEmpty()) {
            throw new StorageException("File cannot be empty!");
        }

        this.rootLocation = Path.of(storageProperties.getDirectory());
    }


    @Override
    public void init() {

        try {
            Files.createDirectories(rootLocation);
        } catch (IOException ex) {
            log.error("Could not initiate storage: ", ex);
            throw new StorageException("Could not initialize storage");
        }
    }


    @Override
    public void store(MultipartFile file) {

        if (file.isEmpty()) {
            throw new StorageException("File cannot be empty");


        }

        try {
            Path destinationFile = rootLocation.resolve(
                    Path.of(file.getOriginalFilename())).normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {

                throw new StorageException("File cannot be stored outside directory");
            }

            try (
                    InputStream input = file.getInputStream()) {
                Files.copy(input, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
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
        return rootLocation.resolve(filename);
    }



    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }




    @Override
    public void deleteAll() {

    }

}

