package OOP;

import java.util.ArrayList; 


public abstract class Performance {
    protected Singer main_artist;
    protected String performance_name;
    protected int year;
    protected String venue;
    protected ArrayList<MusicTrack> track_list;
    protected ArrayList<BackUpSinger> backup_singer_list;
    protected ArrayList<BackUpDancer> backup_dancer_list;

    public Performance(Singer main_artist,String performance_name,int year,String venue,ArrayList<MusicTrack> track_list,ArrayList<BackUpSinger> backup_singer_list,ArrayList<BackUpDancer> backup_dancer_list){
        this.main_artist = main_artist;
        this.performance_name = performance_name;
        this.year = year;
        this.venue = venue;
        this.track_list = track_list;
        this.backup_singer_list = backup_singer_list;
        this.backup_dancer_list = backup_dancer_list;
    }
    public String getPerformanceName(){
        return this.performance_name;
    }

    public void setPerformanceName(String new_name){
        this.performance_name = new_name;
    }

    public int getYear(){
        return this.year;
    }

    public String getVenue(){
        return this.venue;
    }

    public void addSong(MusicTrack new_song){
        this.track_list.add(new_song);
    }

    public void addBackupSinger(BackUpSinger B_sig){
        this.backup_singer_list.add(B_sig);
    }

    public void addBackupDancer(BackUpDancer B_dac){
        this.backup_dancer_list.add(B_dac);
    }

    public abstract void record();

    public void start(){
        System.out.println("Welcome to the Performance " + performance_name + " by " + main_artist.get_name() + "!");
        main_artist.sing();
        // Each back up singer is backing up the artist
        for (BackUpSinger backupSinger : backup_singer_list) {
            backupSinger.BackUpArtist();
        }
        // Each back up dancer is backing up the artist
        for (BackUpDancer backupDancer : backup_dancer_list) {
            backupDancer.BackUpArtist();
        }

    }
}
