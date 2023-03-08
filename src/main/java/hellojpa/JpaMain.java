package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {


            List<Member> result = em.createQuery(
                    "select m from Member m where m.username like '%kim%'", Member.class
            ).getResultList();

            for (Member member : result) {
                System.out.println("member = " + member);
            }

//            Member member = new Member();
//            member.setUsername("hello");
//            member.setHomeAddress(new Address("homeCity", "street", "10"));
//
//            member.getFavoriteFoods().add("치킨");
//            member.getFavoriteFoods().add("족발");
//            member.getFavoriteFoods().add("피자");
//
//            member.getAddressHistory().add(new AddressEntity("old1", "street", "10"));
//            member.getAddressHistory().add(new AddressEntity("old2", "street", "10"));
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            System.out.println("==================Start==============");
//            Member findMember = em.find(Member.class, member.getId());

//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
//
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
         emf.close();
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2 = " + (m1 instanceof Member));
        System.out.println("m1 == m2 = " + (m2 instanceof Member));
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team);
    }
}
