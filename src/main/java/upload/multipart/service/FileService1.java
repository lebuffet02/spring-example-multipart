package upload.multipart.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import upload.multipart.dto.FileDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;


@Service
public class FileService1 {

    public FileDTO upload(MultipartFile file) throws IOException {
        try {
            return getFileDTO(file);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private FileDTO getFileDTO(MultipartFile file) throws IOException {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setName(file.getOriginalFilename());
        fileDTO.setContentType(file.getContentType());
        fileDTO.setSize(file.getSize());
        fileDTO.setBytes(file.getBytes());
        return fileDTO;
    }
}