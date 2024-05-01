package sample.cafekiosk.spring.api.service.mail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.mockito.Mockito.*;

/**
 * Mock과 Spy의 차이
 * - 예를 들어 mailSendClient에서 a, b, c의 메서드가 있는데 현재 지금 나는 sendEmail 메서드만 목으로 사용하고,
 * 나머지 메서드 a, b, c도 실제 객체로 호출하게 할거라면 Spy를 사용하고 sendEmail만 mock으로 사용할 것이라면 Mock을 하면됨.
 */
@ExtendWith(MockitoExtension.class)
class MailServiceTest {

//    @Spy
    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    // 위에 있는 Mock들을 DI처럼 주입함
    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
//        MailSendClient mailSendClient = mock(MailSendClient.class);
//        MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);
//        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

//        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
//                .thenReturn(true);

        // Spy 애노테이션을 쓰면 when을 쓰면 안된다.
//        doReturn(true)
//                .when(mailSendClient)
//                .sendEmail(anyString(), anyString(), anyString(), anyString());

        // 45번쨰줄에 when이라고 써있어서 이상하다 -> 그래서 BDDMockito를 만들었음.
        // BddMockito는 Mockito를 상속하고 있고 그냥 BDD 스타일로 이름만 변경한 것.
        BDDMockito.given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
            .willReturn(true);

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        Assertions.assertThat(result).isTrue();
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}