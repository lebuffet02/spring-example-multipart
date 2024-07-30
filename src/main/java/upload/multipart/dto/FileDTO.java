package upload.multipart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO {

    private String name;
    private String contentType;
    private long size;
    private byte[] bytes;
}
