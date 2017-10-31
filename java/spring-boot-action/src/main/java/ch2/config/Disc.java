package ch2.config;

public class Disc {
    private String name;
    private String title;

    public Disc(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public void Play() {
        System.out.println("My name is " + name + " and title is: " + title);
    }
}
