package at.ldnfuture.kopfsystem.utils;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 10.05.2021
 */
public class PlayerKopf {

    private String uuid;
    private Long time;

    public PlayerKopf(String uuid, Long time){
        this.uuid = uuid;
        this.time = time;
    }

    public String getUuid() {
        return uuid;
    }

    public Long getTime() {
        return time;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
