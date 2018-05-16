package Models;

import Entities.Customer;
import Util.ConnectionHibernate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegister extends ConnectionHibernate {

    public boolean checkUsernameInDB(String username) {
        init();
        Criteria criteria = session.createCriteria(Customer.class).
                add(Restrictions.eq("name", username));
        Customer customer = (Customer) criteria.uniqueResult();
        transaction.commit();
        if (customer != null) return true;
        else return false;
    }

    public boolean checkPhoneNumberInDB(String phoneNumber) {
        init();
        Criteria criteria = session.createCriteria(Customer.class).
                add(Restrictions.eq("phone", phoneNumber));
        Customer customer = (Customer) criteria.uniqueResult();
        transaction.commit();
        if (customer != null) return true;
        else return false;
    }

    public boolean checkEmailInDB(String email) {
        init();
        Criteria criteria = session.createCriteria(Customer.class).
                add(Restrictions.eq("email", email));
        Customer customer = (Customer) criteria.uniqueResult();
        transaction.commit();
        if (customer != null) return true;
        else return false;
    }


    public boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(".+@.+\\..+");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) return true;
        else return false;
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("[1-9][0-9]{8}");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) return true;
        else return false;
    }

    public boolean checkPasswords(String password, String secondPassword) {
        if (password.equals(secondPassword)) return true;
        else return false;
    }

    public boolean checkUsername(String username){
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(username);
        if (matcher.matches()) return true;
        else return false;
    }

     public boolean checkAddress(String address){
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(address);
        if (matcher.matches()) return true;
        else return false;
    }

    public boolean checkPassword(String password){
        Pattern pattern = Pattern.compile("\\w{8,}");
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) return true;
        else return false;
    }

    public Customer addToDB(String username, String password, String email, String phoneNumber) {

        Customer customer = new Customer();
        customer.setName(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setPhone(phoneNumber);

        return customer;
    }
}
