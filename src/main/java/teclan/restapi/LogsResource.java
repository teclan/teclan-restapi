package teclan.restapi;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public class LogsResource extends CorResource {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(LogsResource.class);

    private Event event;

    @Post("json")
    public void create(Event event) {
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Origin",
                "*");
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Methods",
                "POST,OPTIONS");

        LOGGER.info("time:{}\ndetail:{}\nstatus:{}", event.getTime(),
                event.getDetail(), event.getStatus());

    }

    @Post("json")
    public void syncTime(String time) {
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Origin",
                "*");
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Methods",
                "POST,OPTIONS");

        LOGGER.info("time:{}\n", time);

    }

    @Get("json")
    public String detroy() {
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Origin",
                "*");
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Methods",
                "Get,OPTIONS");

        try {
            return new JSONObject().put("STATUS", "destroy").toString();
        } catch (JSONException e) {
            return "";
        }

    }

}
