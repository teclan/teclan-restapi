package teclan.restapi;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import teclan.restapi.RestAPiFactory;

public class restapiTest {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(restapiTest.class);

    @Before
    public void setUp() {
        try {
            RestAPiFactory.getInstance().start();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @After
    public void clean() {

        try {
            RestAPiFactory.getInstance().stop();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Test
    public void PutTest() {
        new LogEvent(new Date(), "测试事件", "成功").report();
    }

    @Test
    public void GetTest() {
        new LogEvent().destroy();
    }

    @Test
    public void fetchTest() {
        new LogEvent().fetch(8000);
    }

    @Test
    public void syncTime() {
        new LogEvent().syncTime();

    }

}
