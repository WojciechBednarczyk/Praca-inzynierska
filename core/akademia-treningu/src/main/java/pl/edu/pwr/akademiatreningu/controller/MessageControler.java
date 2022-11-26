package pl.edu.pwr.akademiatreningu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.akademiatreningu.dto.MessageDto;
import pl.edu.pwr.akademiatreningu.service.MessageService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class MessageControler {

    private final MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity postController(
            @RequestBody MessageDto messageDto) {

        messageService.saveMessage(messageDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
