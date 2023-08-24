package OOP;

import java.util.ArrayList; 

public class LivePerformance extends Performance {
    
    public LivePerformance(Singer main_artist,String performance_name,int year,String venue,ArrayList<MusicTrack> track_list,ArrayList<BackUpSinger> backup_singer_list,ArrayList<BackUpDancer> backup_dancer_list){
            super(main_artist, performance_name, year, venue,track_list,backup_singer_list,backup_dancer_list);
    }

    public void audienceInteract(){
        System.out.println("This is the method audianceInteract() in the class LiverPerfomance");
    }
    @Override
    public void record() {
        System.out.println("This is the method record() in the class LivePerformance");

    
    }
}
