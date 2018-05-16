package Models;

import Entities.Customer;
import Util.ConnectionHibernate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerLogin extends ConnectionHibernate {

    public boolean verifyClient(String username, String password) {
        init();

        Criteria criteria = session.createCriteria(Customer.class).add(Restrictions.eq("name", username))
                .add(Restrictions.eq("password", password));
        Customer customer = (Customer) criteria.uniqueResult();
        transaction.commit();
        if (customer != null) return true;
        else return false;
    }

    public void saveConfig(boolean selected, String username, String password) throws IOException {
        FileWriter fileWriter = new FileWriter("login.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        if (selected) {
            printWriter.print(username);
            printWriter.print(" ");
            printWriter.print(password);
            printWriter.close();
        } else {
            printWriter.print("");
            printWriter.close();
        }
    }

    public Customer getCustomer(String username) {
        init();
        Criteria criteria = session.createCriteria(Customer.class).add(Restrictions.eq("name", username));
        Customer customer  = (Customer) criteria.uniqueResult();
        transaction.commit();
        return customer;
    }

    public static boolean checkConfig() throws NullPointerException{
        if(getFromFile()!=null) return true;
        else return false;
    }


    public static List<String> getLoginInputs() {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(getFromFile());
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    private static String getFromFile() {
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("login.txt");
            bufferedReader = new BufferedReader(fileReader);

            String inputString = bufferedReader.readLine();

            return inputString;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
