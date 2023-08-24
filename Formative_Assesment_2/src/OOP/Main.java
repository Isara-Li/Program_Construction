package OOP;

import java.util.ArrayList; 

public class Main {
    public static void main(String[] args){
        ArrayList<MusicTrack> track_list0= new ArrayList<MusicTrack>();
        ArrayList<BackUpSinger> backup_singer_list0 = new ArrayList<BackUpSinger>();
        ArrayList<BackUpDancer> backup_dancer_list0 = new ArrayList<BackUpDancer>();

        // Creating the main singer.
        Singer main_singer = new Singer("Taylor Swift");

        // Creating the backup singers
        BackUpSinger b_sig_1 = new BackUpSinger("Jeslyn");
        BackUpSinger b_sig_2 = new BackUpSinger("Melanie");

        // Creating the backup dancers
        BackUpDancer b_dac_1 = new BackUpDancer("Melanie");
        BackUpDancer b_dac_2 = new BackUpDancer("Jake");

        //Creating the music tracks.
        MusicTrack song_1 = new MusicTrack("Lavender Haze", 1.0);
        MusicTrack song_2 = new MusicTrack("All Too Well", 2.0);
        MusicTrack song_3 = new MusicTrack("The lakes", 3.0);
        MusicTrack song_4 = new MusicTrack("The Man", 4.0);
        MusicTrack song_5 = new MusicTrack("Love Story", 5.0);

        LivePerformance live_p = new LivePerformance(main_singer, "Eras Tour", 2023, "Glendale", track_list0, backup_singer_list0, backup_dancer_list0);
        
        live_p.addSong(song_1);
        live_p.addSong(song_2);
        live_p.addSong(song_3);
        live_p.addSong(song_4);
        live_p.addSong(song_5);

        live_p.addBackupSinger(b_sig_1);
        live_p.addBackupSinger(b_sig_2);

        live_p.addBackupDancer(b_dac_1);
        live_p.addBackupDancer(b_dac_2);

        live_p.start();
    }
}
