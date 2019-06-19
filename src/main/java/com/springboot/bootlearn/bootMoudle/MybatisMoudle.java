package com.springboot.bootlearn.bootMoudle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


public class MybatisMoudle {
    private String id;
    private String queue_name;
    private String queue_status;
    private String redis_name;
    private String redis_status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQueue_name() {
        return queue_name;
    }

    public void setQueue_name(String queue_name) {
        this.queue_name = queue_name;
    }

    public String getQueue_status() {
        return queue_status;
    }

    public void setQueue_status(String queue_status) {
        this.queue_status = queue_status;
    }

    public String getRedis_name() {
        return redis_name;
    }

    public void setRedis_name(String redis_name) {
        this.redis_name = redis_name;
    }

    public String getRedis_status() {
        return redis_status;
    }

    public void setRedis_status(String redis_status) {
        this.redis_status = redis_status;
    }
}
