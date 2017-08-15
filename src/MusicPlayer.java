import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Reaper on 6/3/17.
 */
public class MusicPlayer {
    InputStream inputStream;
    AudioStream BGM;
    AudioPlayer MGP = AudioPlayer.player;
    ContinuousAudioDataStream loop = null;
    String filePath;


    public MusicPlayer(String filePath){
        this.filePath = filePath;
    }

    public void Play(){
        try
        {
            this.inputStream = new FileInputStream(filePath);
            this.BGM = new AudioStream(inputStream);
        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }

        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        System.out.println("hit");
        AudioPlayer.player.start(BGM);
        MGP.start(loop);
    }
}
