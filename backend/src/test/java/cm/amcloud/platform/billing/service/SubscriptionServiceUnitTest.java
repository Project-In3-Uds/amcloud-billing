package cm.amcloud.platform.billing.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import cm.amcloud.platform.billing.dto.SubscriptionRequest;
import cm.amcloud.platform.billing.model.Subscription;
import cm.amcloud.platform.billing.repository.SubscriptionRepository;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceUnitTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionService subscriptionService;

    private SubscriptionRequest testRequest;
    private Subscription testSubscription;

    @BeforeEach
    void setUp() {
        testRequest = new SubscriptionRequest();
        testRequest.setAgencyName("AgenceAlpha");
        testRequest.setPlanType("Premium");

        testSubscription = new Subscription();
        testSubscription.setId(1L);
        testSubscription.setAgencyName("AgenceAlpha");
        testSubscription.setPlanType("Premium");
        testSubscription.setStartDate(LocalDate.now());
        testSubscription.setEndDate(LocalDate.now().plusMonths(1));
        testSubscription.setActive(true);
    }

    @Test
    void givenSubscriptionRequest_whenCreateSubscription_thenReturnsSavedSubscription() {
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(testSubscription);

        Subscription saved = subscriptionService.createSubscription(testRequest);

        assertNotNull(saved);
        assertEquals("AgenceAlpha", saved.getAgencyName());
        assertEquals("Premium", saved.getPlanType());
        assertTrue(saved.isActive());
        verify(subscriptionRepository, times(1)).save(any(Subscription.class));
    }

    @Test
    void givenActiveSubscription_whenIsSubscriptionValid_thenReturnsTrue() {
        when(subscriptionRepository.findByAgencyNameAndActiveTrue("AgenceAlpha"))
                .thenReturn(Optional.of(testSubscription));

        boolean valid = subscriptionService.isSubscriptionValid("AgenceAlpha");
        assertTrue(valid);
        verify(subscriptionRepository, times(1)).findByAgencyNameAndActiveTrue("AgenceAlpha");
    }

    @Test
    void givenInactiveOrExpiredSubscription_whenIsSubscriptionValid_thenReturnsFalse() {
        Subscription expired = new Subscription();
        expired.setAgencyName("AgenceBeta");
        expired.setActive(true);
        expired.setEndDate(LocalDate.now().minusDays(1));
        when(subscriptionRepository.findByAgencyNameAndActiveTrue("AgenceBeta"))
                .thenReturn(Optional.of(expired));

        boolean valid = subscriptionService.isSubscriptionValid("AgenceBeta");
        assertFalse(valid);
    }

    @Test
    void whenGetAllSubscriptions_thenReturnsList() {
        Subscription sub2 = new Subscription();
        sub2.setAgencyName("AgenceBeta");
        sub2.setPlanType("Standard");
        sub2.setActive(true);

        List<Subscription> mockList = Arrays.asList(testSubscription, sub2);
        when(subscriptionRepository.findAll()).thenReturn(mockList);

        List<Subscription> result = subscriptionService.getAllSubscriptions();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(mockList, result);
        verify(subscriptionRepository, times(1)).findAll();
    }
}
