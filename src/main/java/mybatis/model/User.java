package mybatis.model;

/**
 * Created by Thomas Leruth on 10/30/17
 */

public class User {

    private int id;
    private String first_name;
    private String last_name;
    private int is_active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public User(String first_name, String last_name, int is_active) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.is_active = is_active;
    }

    public User(int id, String first_name, String last_name, int is_active) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.is_active = is_active;
    }

    public User() {
    }
}
