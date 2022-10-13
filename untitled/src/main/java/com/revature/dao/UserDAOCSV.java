package com.revature.dao;
import com.revature.models.Users;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class UserDAOCSV implements UserDAO{


    @Override
    public Users getByEmail(String email) {
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/users.csv"));

            //allowing us to read through file until ended
            while ((line = br.readLine()) != null) {

                //This is where we'll do our logic to split it. Check each line to see if user matches input user
                //Should be able to send user back to client side.
                String[] info = line.split(splitBy);
                if (info[3].equals(email)) {
                    //parse info[0] into integer with method below
                    return new Users(Integer.parseInt(info[0]), info[1], info[2], info[3], info[4], Boolean.parseBoolean(info[5]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users createUser(String first, String last, String email, String password, Boolean isManager) {
        return null;
    }

    @Override
    public List<Users> getAllUsers() {
        return null;
    }

    @Override
    public boolean checkLogin (String email, String password) {
        return false;
    }

//    @Override
//    public Users updateuser(Users user, int isUpdated) {
//        return null;
//    }


}