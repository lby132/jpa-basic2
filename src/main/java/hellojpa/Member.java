package hellojpa;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
@Data
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TEAM_ID")
    private Long teamId;

}
