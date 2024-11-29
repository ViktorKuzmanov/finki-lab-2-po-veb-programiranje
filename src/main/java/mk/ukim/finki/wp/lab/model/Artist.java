package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// TODO: mozis da gi napisis manualno isto taka via metodite namesto da koristish @Data anotacija
@Data
@AllArgsConstructor
public class Artist {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
}
