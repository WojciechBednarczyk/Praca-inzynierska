package pl.edu.pwr.akademiatreningu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.akademiatreningu.dto.MessageDto;
import pl.edu.pwr.akademiatreningu.dto.MessageWithSenderDto;
import pl.edu.pwr.akademiatreningu.service.MessageService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class MessageControler {

    private final MessageService messageService;

    @PostMapping("/message")
    @PreAuthorize("hasRole('ROLE_MENTEE') or hasRole('ROLE_PERSONAL_TRAINER')")
    public ResponseEntity postMessage(
            @RequestBody MessageDto messageDto) {

        messageService.saveMessage(messageDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/messages")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_MENTEE') or hasRole('ROLE_PERSONAL_TRAINER')")
    public List<MessageWithSenderDto> getMessages(@RequestParam Integer userId) {
        return messageService.getUserMessages(userId);
    }
}
