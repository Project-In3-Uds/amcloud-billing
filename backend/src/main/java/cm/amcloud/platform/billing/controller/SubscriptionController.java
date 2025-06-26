package cm.amcloud.platform.billing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader; // Import added
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cm.amcloud.platform.billing.dto.SubscriptionRequest;
import cm.amcloud.platform.billing.model.Subscription;
import cm.amcloud.platform.billing.service.SubscriptionService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/create")
    public Subscription create(@RequestBody SubscriptionRequest request,
                               @RequestHeader(name = "X-User-ID", required = false) String userId,
                               @RequestHeader(name = "X-User-Roles", required = false) String userRoles,
                               @RequestHeader(name = "X-User-Scopes", required = false) String userScopes) {
        System.out.println("User ID: " + userId + ", Roles: " + userRoles + ", Scopes: " + userScopes + " is creating a subscription.");
        return subscriptionService.createSubscription(request);
    }

    @GetMapping("/list")
    public List<Subscription> getAll(
            @RequestHeader(name = "X-User-ID", required = false) String userId,
            @RequestHeader(name = "X-User-Roles", required = false) String userRoles,
            @RequestHeader(name = "X-User-Scopes", required = false) String userScopes) {
        System.out.println("User ID: " + userId + ", Roles: " + userRoles + ", Scopes: " + userScopes + " is requesting all subscriptions.");
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/{agencyName}/valid")
    public boolean isValid(@PathVariable String agencyName,
                           @RequestHeader(name = "X-User-ID", required = false) String userId,
                           @RequestHeader(name = "X-User-Roles", required = false) String userRoles,
                           @RequestHeader(name = "X-User-Scopes", required = false) String userScopes) {
        System.out.println("User ID: " + userId + ", Roles: " + userRoles + ", Scopes: " + userScopes + " is checking subscription validity for " + agencyName);
        return subscriptionService.isSubscriptionValid(agencyName);
    }
}
