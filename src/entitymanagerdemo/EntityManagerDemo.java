/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitymanagerdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Address;
import model.Customer;

/**
 *
 * @author sarun
 */
public class EntityManagerDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //createData();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
       
        em.getTransaction().begin();
        try {
            //em.persist(address);
            //em.persist(customer);
            //em.flush();
            //em.persist(address);
            //em.persist(customer);
            //em.refresh(customer);
            //em.detach(customer);
            //em.persist(customer);
            //em.merge(customer);
            //em.remove(customer);
            //em.persist(customer);
            //em.remove(address);
            printAllCustomer();
            printCustomerByCity("Bangkok");
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static void createData(){
        Customer[] cus = new Customer[4];
        Address[] address = new Address[10];
        
        cus[0] = new Customer(1L, "John", "Henry", "jh@mail.com");       
        cus[1] = new Customer(2L, "Marry", "Jane", "mj@mail.com");   
        cus[2] = new Customer(3L, "Peter", "Parker", "pp@mail.com");  
        cus[3] = new Customer(4L, "Bruce", "Wayn", "bw@mail.com"); 
        
        address[0] = new Address(1L, "123/4 Viphavadee Rd", "Bangkok", "10900", "TH");  
        address[1] = new Address(2L, "123/5 Viphavadee Rd", "Bangkok", "10900", "TH");  
        address[2] = new Address(3L, "543/21 Fake Rd", "Nonthaburi", "20900", "TH"); 
        address[3] = new Address(4L, "678/90 Unreal Rd", "Pathumthani", "30500", "TH"); 
        
        address[0].setCustomerFk(cus[0]);
        cus[0].setAddressId(address[0]);
        
        address[1].setCustomerFk(cus[1]);
        cus[1].setAddressId(address[1]); 
        
        address[2].setCustomerFk(cus[2]);
        cus[2].setAddressId(address[2]);
        
        address[3].setCustomerFk(cus[3]);
        cus[3].setAddressId(address[3]); 
 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try {
            for(int i=0;i<cus.length;i++){
                em.persist(address[i]);
                em.persist(cus[i]);
            }         
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static void printAllCustomer(){
        List<Customer> cusList = findAllCus();
        List<Address> addressList = findAllAddress();
        
        for(Customer cus:cusList){
            for(Address address:addressList){
                if(cus.getAddressId().getId().equals(address.getId())){
                    System.out.println("First Name : " + cus.getFirstname());
                    System.out.println("Last Name : " + cus.getLastname());
                    System.out.println("Email : " + cus.getEmail());
                    
                    System.out.println("Street : " + address.getStreet());
                    System.out.println("City : " + address.getCity());
                    System.out.println("Country : " + address.getCountry());
                    System.out.println("Zimcode : " + address.getZipcode());
                    System.out.println();
                }    
            }
        }
        System.out.println("==========================================================");
    }
    
     public static void printCustomerByCity(String city){
        List<Customer> cusList = findAllCus();
        List<Address> addressList = findAllAddressByCity(city);
     
        for(Customer cus:cusList){
            for(Address address:addressList){
                if(cus.getAddressId().getId().equals(address.getId())){
                    System.out.println("First Name : " + cus.getFirstname());
                    System.out.println("Last Name : " + cus.getLastname());
                    System.out.println("Email : " + cus.getEmail());
                    
                    System.out.println("Street : " + address.getStreet());
                    System.out.println("City : " + address.getCity());
                    System.out.println("Country : " + address.getCountry());
                    System.out.println("Zimcode : " + address.getZipcode());
                    System.out.println();
                }    
            }
        }
        System.out.println("==========================================================");
    }
    
    public static List<Customer> findAllCus(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Customer.findAll");
        List<Customer> cusList = (List<Customer>) query.getResultList();
        return cusList;
    }
    
    public static List<Address> findAllAddress(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Address.findAll");
        List<Address> addressList = (List<Address>) query.getResultList();
        return addressList;
    }
    
    public static List<Address> findAllAddressByCity(String city){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Address.findByCity");
        query.setParameter("city",city);
        List<Address> addressList = (List<Address>) query.getResultList();
        return addressList;
    }

    
}
