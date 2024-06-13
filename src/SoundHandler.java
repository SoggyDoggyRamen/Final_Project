import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SoundHandler {

    private float sound;
    private FloatControl pew1Control, pew2Control, pew3Control;
    private Clip pew1, pew2, pew3;

    public SoundHandler() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        pew1 = AudioSystem.getClip();
        pew2 = AudioSystem.getClip();
        pew3 = AudioSystem.getClip();
        pew1.open(AudioSystem.getAudioInputStream(new File("Audio/pew1.wav").getAbsoluteFile()));
        pew2.open(AudioSystem.getAudioInputStream(new File("Audio/pew2.wav").getAbsoluteFile()));
        pew3.open(AudioSystem.getAudioInputStream(new File("Audio/pew3.wav").getAbsoluteFile()));
        sound = -14.0f;
        pew1Control = (FloatControl) pew1.getControl((FloatControl.Type.MASTER_GAIN));
        pew2Control = (FloatControl) pew2.getControl((FloatControl.Type.MASTER_GAIN));
        pew3Control = (FloatControl) pew3.getControl((FloatControl.Type.MASTER_GAIN));
    }

    public void update() {
        pew1Control.setValue(sound);
        pew2Control.setValue(sound);
        pew3Control.setValue(sound);
    }

    public void playPew1() {
        pew1.setMicrosecondPosition(200000);
        pew1.start();
    }

    public void playPew2() {
        pew2.setMicrosecondPosition(450000);
        pew2.start();
    }

    public void playPew3() {
        pew3.setMicrosecondPosition(0);
        pew3.start();
    }
}