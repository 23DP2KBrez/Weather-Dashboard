package lv.rvt;

public class UserService 
{
    boolean initialized = false;

    public void initialize()
    {
        initialized = true;
    }

    public UserData getUserById(int id)
    {
        if(!initialized) return null;
        return new UserData();
    }

    public UserData getUserByEmail(String email)
    {
        if(!initialized) return null;
        return new UserData();
    }

    public void addUser(UserData data)
    {

    }
}
