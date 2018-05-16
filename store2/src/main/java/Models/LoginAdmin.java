package Models;

import java.util.HashMap;
import java.util.Map;

public class LoginAdmin {
    public boolean checkInputs(String login, String password) {
        Map<String, String> map = getRightData();
        if(map.containsKey(login)){
            if(password.equals(map.get(login))){
                return true;
            }
            else return false;
        }
        else return false;


    }

    private Map<String, String> getRightData() {
        Map<String, String> map = new HashMap<>();
        map.put("Admin", "123");
        return map;
    }

}
