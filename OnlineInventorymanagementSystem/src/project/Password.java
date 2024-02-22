package project;
import org.mindrot.jbcrypt.BCrypt;
public class Password {
	public String hashPassword(String plainTextPassword) {
	    int numberOfRounds = 12;
	    String salt = BCrypt.gensalt(numberOfRounds);
	    return BCrypt.hashpw(plainTextPassword, salt);
	}
	  public boolean checkPassword(String enteredPassword,String storedPassword) {
	        
	        if (BCrypt.checkpw(enteredPassword, storedPassword))
	        	{
	            System.out.println("Password match, authentication successful");
	            return true;
	        } else {
	            System.out.println("Password does not match, authentication failed");
	            return false;
	        }
	    }
}

