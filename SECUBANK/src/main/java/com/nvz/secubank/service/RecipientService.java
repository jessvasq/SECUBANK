package com.nvz.secubank.service;

import com.nvz.secubank.dto.RecipientDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecipientService {
    void addRecipient(RecipientDto recipientDto);
    RecipientDto getRecipientById(Long recipientId);
    List<RecipientDto> getAllRecipients(Long userId);
    RecipientDto updateRecipient(Long recipientId, RecipientDto recipientDto);
    void deleteRecipient(Long recipientId);
}
