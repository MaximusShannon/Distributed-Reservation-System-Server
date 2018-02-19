package functionality;

import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;

public class DatabaseInteractionService {

    private User userReceived;
    private User placeHolderUser;
    private EntityManagerFactory emf;
    private EntityManager em;
    private ArrayList<User> userList;

    public DatabaseInteractionService(){

        initPersistence();
        getUserList();
    }

    public boolean checkDoesUserExist(User userReceived){

        boolean exists = false;

        if(userList.size() > 0){

            for(int i = 0; i < userList.size(); i++){

                if(userList.get(i).getEmail().equals(userReceived.getEmail())){
                    exists = true;

                }
            }
        }

        return exists;
    }

    public void persistUser(User userReceived){

        //em.getTransaction().begin();
        em.persist(userReceived);
        em.getTransaction().commit();
        em.close();
    }

    private void initPersistence(){

        emf = Persistence.createEntityManagerFactory("UserDetails_JPA");
        em = emf.createEntityManager();
        userList = new ArrayList<>();
    }

    private void getUserList(){

        int sizeOfTable;
        Query getTableSize;
        Long tableSize;

        em.getTransaction().begin();
        getTableSize = em.createNativeQuery("SELECT COUNT(*) FROM USER ");
        tableSize =  (Long) getTableSize.getSingleResult();
        sizeOfTable = Integer.parseInt(tableSize.toString());

        if(sizeOfTable > 0){
            for(int i = 1; i <= sizeOfTable; i++){

                placeHolderUser = em.find(User.class, i);
                userList.add(placeHolderUser);
            }
        }
    }

    public User getExistingUser(String email){

        User userFound = null;

        if(userList.size() > 0){

            for(int i = 0; i < userList.size(); i++){

                if(userList.get(i).getEmail().equals(email)){
                    userFound = userList.get(i);
                }
            }
        }
        return userFound;

    }

}
