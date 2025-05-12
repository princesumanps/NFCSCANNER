package com.paper.ModelClases;

public class ServicesPojo {
    String ServiceName,ExpiryDate,Type,ServiceLogoimage,ServiceRemains,ServiceCost,createddate,NoOfServices,ServiceDuration,RefNo;

    public ServicesPojo() {
    }

    public ServicesPojo(String serviceName, String expiryDate, String type, String serviceLogoimage, String serviceRemains, String serviceCost, String createddate, String noOfServices, String serviceDuration, String refNo) {
        ServiceName = serviceName;
        ExpiryDate = expiryDate;
        Type = type;
        ServiceLogoimage = serviceLogoimage;
        ServiceRemains = serviceRemains;
        ServiceCost = serviceCost;
        this.createddate = createddate;
        NoOfServices = noOfServices;
        ServiceDuration = serviceDuration;
        RefNo = refNo;
    }

    public String getServiceCost() {
        return ServiceCost;
    }

    public void setServiceCost(String serviceCost) {
        ServiceCost = serviceCost;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public String getNoOfServices() {
        return NoOfServices;
    }

    public void setNoOfServices(String noOfServices) {
        NoOfServices = noOfServices;
    }

    public String getServiceDuration() {
        return ServiceDuration;
    }

    public void setServiceDuration(String serviceDuration) {
        ServiceDuration = serviceDuration;
    }

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String refNo) {
        RefNo = refNo;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getServiceLogoimage() {
        return ServiceLogoimage;
    }

    public void setServiceLogoimage(String serviceLogoimage) {
        ServiceLogoimage = serviceLogoimage;
    }

    public String getServiceRemains() {
        return ServiceRemains;
    }

    public void setServiceRemains(String serviceRemains) {
        ServiceRemains = serviceRemains;
    }
}
