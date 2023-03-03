package hellojpa;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
