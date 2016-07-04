package teclan.restapi;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.monoid.json.JSONObject;
import us.monoid.web.Content;
import us.monoid.web.Resty;

public class LogEvent {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(LogEvent.class);

    private static final String ROUT_EVENTS_REPORT  = "http://127.0.0.1:6021/v1/logs";
    private static final String ROUT_EVENTS_DESTROY = "http://127.0.0.1:6021/v1/destroy";
    private static final String ROUT_EVENTS_FETCH   = "http://127.0.0.1:6021/v1/fetch/%s";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private String time;
    private String detail;
    private String status;

    /**
     * 构造代理事件对象
     * 
     * @param time
     *            事件时间
     * @param detail
     *            事件描述
     * @param status
     *            状态
     */
    public LogEvent(Date date, String detail, String status) {
        this.time = dateFormat.format(date);
        this.detail = detail;
        this.status = status;
    }

    public LogEvent() {
        // TODO Auto-generated constructor stub
    }

    public synchronized LogEvent set(Date date, String detail, String status) {

        this.time = dateFormat.format(date);
        this.detail = detail;
        this.status = status;

        return this;
    }

    public void report() {

        JSONObject json = new JSONObject();

        try {
            json.put("event", new JSONObject().put("time", time)
                    .put("detail", detail).put("status", status));

            new Resty().json(ROUT_EVENTS_REPORT,
                    new Content("application/json; charset=utf-8",
                            json.toString().getBytes("UTF-8")));

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void syncTime() {
        JSONObject json = new JSONObject();

        try {
            json.put("time", dateFormat.format(new Date()).toString());

            new Resty().json(ROUT_EVENTS_REPORT,
                    new Content("application/json; charset=utf-8",
                            json.toString().getBytes("UTF-8")));

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public void destroy() {
        try {

            JSONObject result = new Resty().json(ROUT_EVENTS_DESTROY).object();

            LOGGER.info(result.toString());

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void fetch(int id) {

        try {

            JSONObject result = new Resty()
                    .json(String.format(ROUT_EVENTS_FETCH, id)).object();
            LOGGER.info(result.toString());

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
