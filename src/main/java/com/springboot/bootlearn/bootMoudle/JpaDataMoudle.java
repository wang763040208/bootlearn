package com.springboot.bootlearn.bootMoudle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "spboot_myb")
public class JpaDataMoudle {
    @Id
    @GeneratedValue(generator   = "myIdStrategy")
    @GenericGenerator(name = "myIdStrategy", strategy = "uuid")
    private String id;
    private String queueName;
    private Integer queueStatus;
    private String redisName;
    private Integer redisStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Integer getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(Integer queueStatus) {
        this.queueStatus = queueStatus;
    }

    public String getRedisName() {
        return redisName;
    }

    public void setRedisName(String redisName) {
        this.redisName = redisName;
    }

    public Integer getRedisStatus() {
        return redisStatus;
    }

    public void setRedisStatus(Integer redisStatus) {
        this.redisStatus = redisStatus;
    }
}
