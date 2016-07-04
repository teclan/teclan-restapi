package teclan.restapi;

import org.restlet.resource.Get;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public class FetchResources extends CorResource {

    @Get("json")
    public String fecth() {
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Origin",
                "*");
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Methods",
                "Get,OPTIONS");

        try {
            return new JSONObject().put("LOG", "测试对象:" + getAttribute("id"))
                    .toString();
        } catch (JSONException e) {
            return "";
        }

    }

}
