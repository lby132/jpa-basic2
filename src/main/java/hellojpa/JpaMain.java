package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("refMember = " + refMember.getClass());  //Proxy

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getClass());   //Member

            System.out.println("findMember == refMember :" + (findMember == refMember));
//
//            Member m1 = em.find(Member.class, member.getId());
//            Member m2 = em.getrefMember(Member.class, member2.getId());
//
//            logic(m1, m2);

            // refMember의 프록시가 초기화 되었는지 확인함.
            System.out.println("isLoaded = "+ emf.getPersistenceUnitUtil().isLoaded(refMember));
            Hibernate.initialize(refMember); // 강제 초기화

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
