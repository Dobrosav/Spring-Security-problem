package rs.yettel.roamingbarring;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.yettel.roamingbarring.client.bms.BmsService;
import rs.yettel.roamingbarring.client.cbio.CbioService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class RoamingBarringApplicationTests {

    @Test
    void contextLoads() {
    }

    private Logger logger = LoggerFactory.getLogger(RoamingBarringApplicationTests.class);
    @Autowired
    private BmsService bmsService;
    @Autowired
    private CbioService cbioService;

    @Test
    void testBMS() {
        logger.info("bms result {}", bmsService.roamingEligible("6.205006"));
    }

    @Test
    void testCBIO() {
        List<String> services = Arrays.asList("roaming-data");
        logger.error("cbio result = {}", cbioService.getRoamingStatusesByName("381638618296", services));
    }
}
