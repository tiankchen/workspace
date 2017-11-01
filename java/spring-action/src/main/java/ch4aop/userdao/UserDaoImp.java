package ch4aop.userdao;

public class UserDaoImp implements UserDao {

    @Override
    public int addUser() {
     System.out.println("Add user ...");
        return 0;
    }

    @Override
    public void updateUser() {
        System.out.println("Update user ...");

    }

    @Override
    public void deleteUser() {
        System.out.println("Delete user ...");
    }

    @Override
    public void findUser() {
        System.out.println("Find user ...");

    }

}
