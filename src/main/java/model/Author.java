package homework.books.model;

public class Author {
    private String name;
    private String surname;
    private String email;
    private Gender gender;
    private User registeredUser;

    public Author(String name, String surname, String email, Gender gender, User registeredUser) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.registeredUser = registeredUser;
    }

    public Author() {
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", registeredUser='" + registeredUser.getName() + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public User getUserRegistered() {
        return registeredUser;
    }

    public void setUserRegistered(User registeredUser) {
        this.registeredUser = registeredUser;
    }
}