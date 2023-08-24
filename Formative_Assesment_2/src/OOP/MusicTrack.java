package OOP;

public class MusicTrack{
	   private String name;
	   private double duration;

	   public MusicTrack(String name, double duration){
	    this.name = name;
	    this.duration = duration;
	   }

	   public String get_name(){
	    return this.name;
	   }

	   public double get_duration(){
	    return this.duration;
	   }

	}