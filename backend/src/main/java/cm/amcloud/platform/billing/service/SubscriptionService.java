package cm.amcloud.platform.billing.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import cm.amcloud.platform.billing.dto.SubscriptionRequest;
import cm.amcloud.platform.billing.model.Subscription;
import cm.amcloud.platform.billing.repository.SubscriptionRepository;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription createSubscription(SubscriptionRequest request) {
        Subscription subscription = new Subscription();
        subscription.setAgencyName(request.getAgencyName());
        subscription.setPlanType(request.getPlanType());
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(1)); // Exemple: 1 mois
        subscription.setActive(true);

        return subscriptionRepository.save(subscription);
    }

    public boolean isSubscriptionValid(String agencyName) {
        return subscriptionRepository.findByAgencyNameAndActiveTrue(agencyName)
                .map(sub -> sub.getEndDate().isAfter(LocalDate.now()))
                .orElse(false);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }
}
