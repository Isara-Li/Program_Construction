package OOP;

public class BackUpSinger extends Singer implements IBackup {
    
    public BackUpSinger(String name){
        super(name);
    }
    // Back Up Singers can sing.
    public void sing(){
        System.out.println("This is the method sing() in the class BackUpSinger");
    }
    @Override
    public void BackUpArtist() {
        this.sing();
        System.out.println("This is the method BackUpArtist() in the class BackUpSinger");
    }
}