/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package create.webhook.demo;

import com.ringcentral.RestException;
import com.ringcentral.definitions.CreateSubscriptionRequest;
import com.ringcentral.definitions.NotificationDeliveryModeRequest;
import com.ringcentral.definitions.SubscriptionInfo;

import java.io.IOException;
import com.ringcentral.RestClient;

public class App {
  public void getGreeting() throws IOException, RestException {
    RestClient rc = new RestClient(
        "client id here",
        "client secret here",
        "https://platform.ringcentral.com");
    rc.authorize(
        "jwt token here");

    SubscriptionInfo subscriptionInfo = rc.restapi().subscription()
        .post(new CreateSubscriptionRequest().eventFilters(new String[] { "/restapi/v1.0/glip/webhooks" })
            .deliveryMode(new NotificationDeliveryModeRequest().transportType("WebHook")
                .address("Your webhook url here"))
            .expiresIn(63072000L));
    System.out.println(subscriptionInfo.id);
    rc.restapi().subscription(subscriptionInfo.id).delete(); // clean up after testing
    rc.revoke();
  }

  public static void main(String[] args) throws IOException, RestException {
    new App().getGreeting();
  }
}
