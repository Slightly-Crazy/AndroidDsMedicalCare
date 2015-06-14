package Events;

/**
 * Created by wendywang on 15-06-13.
 */
public class Child {
    private String firstname;
    private String lastname;
    private String dob;
    private String gender;
    private String parentId;
    private String bedTime;
    private String id;

    public Child(String firstname,
                 String lastname,
                 String dob,
                 String gender,
                 String parentId,
                 String bedTime,
                 String id){
           this.firstname = firstname;
           this.lastname = lastname;
           this.dob = dob;
           this.gender = gender;
           this.parentId = parentId;
           this.bedTime = bedTime;
           this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getBedTime() {
        return bedTime;
    }

    public void setBedTime(String bedTime) {
        this.bedTime = bedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
