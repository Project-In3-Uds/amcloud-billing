package cm.amcloud.platform.billing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequest {
    private String agencyName;
    private String planType;
}
