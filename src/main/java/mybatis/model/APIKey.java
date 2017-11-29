package mybatis.model;

import java.sql.Timestamp;

/**
 * Created by Thomas Leruth on 11/15/17
 */

public class APIKey {

    private int id;
    private String key;
    private int callCounter;
    private Timestamp lastCallTime = new Timestamp(System.currentTimeMillis());

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCallCounter() {
        return callCounter;
    }

    public void setCallCounter(int callCounter) {
        this.callCounter = callCounter;
    }

    public Timestamp getLastCallTime() {
        return lastCallTime;
    }

    public void setLastCallTime(Timestamp lastCallTime) {
        this.lastCallTime = lastCallTime;
    }

    public APIKey(String key) {
        this.key = key;
    }

    public APIKey(int id, String key, int callCounter, Timestamp lastCallTime) {
        this.id = id;
        this.key = key;
        this.callCounter = callCounter;
        this.lastCallTime = lastCallTime;
    }
}
