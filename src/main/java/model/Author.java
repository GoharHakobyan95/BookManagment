package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private String name;
    private String surname;
    private String email;
    private Gender gender;
    private User registeredUser;

}