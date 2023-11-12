import javax.sound.sampled.*;
//synth sound that is generated when sensors see movement
public class PianoSoundGenerator {
    private AudioFormat audioFormat;
    private SourceDataLine sourceDataLine;

    public PianoSoundGenerator() {
        audioFormat = new AudioFormat(44100, 16, 2, true, true);
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

        try {
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playNote(double frequency, int durationMs) {
        int numSamples = (int) (audioFormat.getSampleRate() * durationMs / 1000.0);
        byte[] buffer = new byte[2 * numSamples];

        for (int i = 0; i < numSamples; i++) {
            double time = i / audioFormat.getSampleRate();
            short amplitude = (short) (Short.MAX_VALUE * Math.sin(2 * Math.PI * frequency * time));
            buffer[2 * i] = (byte) (amplitude & 0xFF);
            buffer[2 * i + 1] = (byte) ((amplitude >> 8) & 0xFF);
        }

        sourceDataLine.write(buffer, 0, buffer.length);
    }

    public void close() {
        sourceDataLine.drain();
        sourceDataLine.close();
    }

    public static void main(String[] args) {
        PianoSoundGenerator piano = new PianoSoundGenerator();
        
        // Play a few notes
        piano.playNote(261.63, 500); // Middle C
        piano.playNote(293.66, 500); // D
        piano.playNote(329.63, 500); // E
        piano.playNote(349.23, 500); // F
        piano.playNote(392.00, 500); // G
        piano.playNote(440.00, 500); // A
        piano.playNote(493.88, 500); // B
        
        piano.close();
    }
}
