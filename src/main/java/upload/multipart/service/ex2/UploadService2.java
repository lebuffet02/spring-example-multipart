package upload.multipart.service.ex2;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UploadService2 {

    private final Path fileStorageLocation = Paths.get("src/main/resources/static").toAbsolutePath().normalize();

    public String uploadService(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        file.transferTo(fileStorageLocation.resolve(fileName));
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/upload2/download/").path(fileName).toUriString();
    }

    public List<String> listFilesService() throws IOException {
        return Files.list(fileStorageLocation).map(Path::getFileName).map(Path::toString).collect(Collectors.toList());
    }
}
