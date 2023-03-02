package hellojpa;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;
}
