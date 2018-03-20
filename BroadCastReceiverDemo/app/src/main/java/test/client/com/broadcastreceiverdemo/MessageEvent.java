package test.client.com.broadcastreceiverdemo;

/**
 * Created by chuck on 2018/3/16.
 */

public class MessageEvent {
    private int age;
    private String name;
    private String password;

    public MessageEvent(int age) {
        this.age = age;
    }

    public MessageEvent(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
