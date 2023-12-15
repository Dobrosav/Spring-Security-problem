package rs.yettel.roamingbarring.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.yettel.roamingbarring.api.model.ServiceProvisionRequest;
import rs.yettel.roamingbarring.api.model.Status;
import rs.yettel.roamingbarring.model.wrapper.ApiResponseWrapper;

import java.util.List;

@RestController
@RequestMapping("/roaming-barring/v1/")
public class RoamingApi {
    private static final Logger logger= LoggerFactory.getLogger(RoamingApi.class);
    @PostMapping(value = "services/{resource}")
    public ResponseEntity<ApiResponseWrapper<Status>> activateBarring(@PathVariable("resource") String resource, @RequestBody List<ServiceProvisionRequest> request){
        Status status = new Status();
        status.setStatus(true);
        logger.info("resource={} request={}", resource,request);
        return new ResponseEntity(new ApiResponseWrapper(status), HttpStatus.OK);
    }
}
