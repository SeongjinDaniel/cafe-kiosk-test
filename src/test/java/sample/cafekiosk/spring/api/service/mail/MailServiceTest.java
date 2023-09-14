package sample.cafekiosk.spring.api.service.mail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.mockito.Mockito.*;

class MailServiceTest {

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
        MailSendClient mailSendClient = mock(MailSendClient.class);
        MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);

        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        Assertions.assertThat(result).isTrue();
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}