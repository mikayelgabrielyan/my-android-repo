package instigatemobile.com.amot;

public class User {
    private String name;
    private String description;
    private String imageURL;
    private String phoneNumber;
    private String emailAdress;

    public User(String name, String description, String imageURL, String phoneNumber, String emailAdress) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        emailAdress = emailAdress;
    }
}
