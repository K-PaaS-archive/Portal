package org.openpaas.paasta.portal.web.admin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 클래스 설명
 *
 * @author nawkm
 * @version 1.0
 * @since 2016.4.4 최초작성
 */

public class Org extends Entity {
    private String orgName;
    private String newOrgName;

    private UUID guid;
    private String name;
    private String status;
    private int memoryUsage;
    private int memoryLimit;


    private List<Space> spaces = new ArrayList<Space>();

    private boolean billingEnabled = false;

    private Quota quota;

    public Org() {
        //empty
    }

    public boolean isBillingEnabled() {
        return billingEnabled;
    }

    public void setBillingEnabled(boolean billingEnabled) {
        this.billingEnabled = billingEnabled;
    }

    public Quota getQuota() {
        return quota;
    }

    public void setQuota(Quota quota) {
        this.quota = quota;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getNewOrgName() {
        return newOrgName;
    }

    public void setNewOrgName(String newOrgName) {
        this.newOrgName = newOrgName;
    }


    public int getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(int memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<Space> spaces) {
        this.spaces = spaces;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}