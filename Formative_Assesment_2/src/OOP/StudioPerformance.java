package OOP;

import java.util.ArrayList; 

public class StudioPerformance extends Performance {
    
    public StudioPerformance(Singer main_artist,String performance_name,int year,String venue,ArrayList<MusicTrack> track_list,ArrayList<BackUpSinger> backup_singer_list,ArrayList<BackUpDancer> backup_dancer_list){
            super(main_artist, performance_name, year, venue,track_list,backup_singer_list,backup_dancer_list);
    }

    public void audioProcessing(){
        System.out.println("This is the method audioProcessing() in the class StudioPerformance");
    }
    @Override
    public void record() {
        System.out.println("This is the method record() in the class LivePerformance");
    }
}
