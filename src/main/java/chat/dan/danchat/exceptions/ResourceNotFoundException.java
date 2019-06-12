package chat.dan.danchat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Danmoji does not exist")
public class ResourceNotFoundException extends RuntimeException {
}
