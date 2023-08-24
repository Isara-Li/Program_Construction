package OOP;

public class Singer extends Artist {
    
    public Singer(String name){
        super(name);
    }

    public void sing(){
        System.out.println("This is the method sing() in the class Singer");
    }
}