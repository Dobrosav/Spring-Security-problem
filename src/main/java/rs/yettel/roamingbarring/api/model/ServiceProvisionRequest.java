package rs.yettel.roamingbarring.api.model;

import java.io.Serializable;

public class ServiceProvisionRequest implements Serializable {
    private ServiceType service;

    public ServiceProvisionRequest() {
    }

    public ServiceType getService() {
        return service;
    }

    public void setService(ServiceType service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "ServiceProvisionRequest{" +
                "service=" + service +
                '}';
    }
}
