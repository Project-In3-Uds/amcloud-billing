package cm.amcloud.platform.billing.repository;

import cm.amcloud.platform.billing.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByAgencyNameAndActiveTrue(String agencyName);
}
