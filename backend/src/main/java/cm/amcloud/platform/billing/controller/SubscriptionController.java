package cm.amcloud.platform.billing.controller;

import cm.amcloud.platform.billing.dto.SubscriptionRequest;
import cm.amcloud.platform.billing.model.Subscription;
import cm.amcloud.platform.billing.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public Subscription create(@RequestBody SubscriptionRequest request) {
        return subscriptionService.createSubscription(request);
    }

    @GetMapping
    public List<Subscription> getAll() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/{agencyName}/valid")
    public boolean isValid(@PathVariable String agencyName) {
        return subscriptionService.isSubscriptionValid(agencyName);
    }
}
