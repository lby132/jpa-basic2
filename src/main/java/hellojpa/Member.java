package hellojpa;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
@Data
@ToString
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void changTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
