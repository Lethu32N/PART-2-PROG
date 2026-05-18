
package loginsystem;

public class Login {

    private String username;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;

 
    public Login(String username, String password, String phoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

  
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

   
    public boolean checkPasswordComplexity() {
        boolean length = password.length() >= 8;
        boolean capital = password.matches(".*[A-Z].*");
        boolean number = password.matches(".*[0-9].*");
        boolean special = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        return length && capital && number && special;
    }

  
    public boolean checkCellPhoneNumber() {
        return phoneNumber.matches("^\\+27\\d{9}$");
    }

    
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "User successfully registered.";
    }

 
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(username) && enteredPassword.equals(password);
    }


    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}