package OOP;

public class BackUpDancer extends Artist implements IBackup{

    public BackUpDancer(String name){
        super(name);
    }

    // Back up dancers can dance.
    public void dance(){
        System.out.println("This is the method dance() in the class BackUpDancer");
    }
    @Override
    public void BackUpArtist() {
        this.dance();
       System.out.println("This is the method BackUpArtist() in the class BackUpDancer");
    }
    
}

