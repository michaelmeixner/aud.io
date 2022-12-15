import javax.sound.sampled.*;
import java.awt.*;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;

public class PlayTone {
    public static void main(String[] args) throws LineUnavailableException {
        // Prompt the user for the frequency of the tone
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the frequency of the tone in Hz: ");
        double frequency = scanner.nextDouble();

        // Prompt the user for the amount of distortion
        System.out.print("Enter the amount of distortion (0-1): ");
        double distortion = scanner.nextDouble();
        scanner.close();

        // Calculate the duration of the tone in milliseconds
        final int duration = 1000; // 1 second

        // Calculate the number of sample points in the tone
        final int sampleRate = 44100;
        int numSamples = duration * sampleRate / 1000;

        // Generate the sample points for the tone
        double[] samples = new double[numSamples];
        for (int i = 0; i < numSamples; i++) {
            samples[i] = Math.sin(2 * Math.PI * i / (sampleRate / frequency));
        }

        // Apply the distortion effect to the samples
        for (int i = 0; i < samples.length; i++) {
            samples[i] = samples[i] * (1 + distortion * (Math.random() - 0.5));
        }

        // Display the waveform of the tone
        GraphPanel panel = new GraphPanel(samples, sampleRate, duration);
        JFrame frame = new JFrame("Waveform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);

        // Convert the sample points into a format that can be played
        AudioFormat audioFormat = new AudioFormat(sampleRate, 16, 1, true, false);
        byte[] data = new byte[2 * numSamples];
        for (int i = 0, j = 0; i < numSamples; i++) {
            int sample = (int) (samples[i] * 32767);
            data[j++] = (byte) (sample & 0xff);
            data[j++] = (byte) ((sample >> 8) & 0xff);
        }

        // Play the tone
        InputStream input = new ByteArrayInputStream(data);
        AudioInputStream audioInputStream = new AudioInputStream(input, audioFormat,
                data.length / audioFormat.getFrameSize());
        Clip clip = AudioSystem.getClip();
        try {
            clip.open(audioInputStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        clip.start();
    }
}

class GraphPanel extends JPanel {
    private double[] samples;
    private int sampleRate;
    private int duration;

    public GraphPanel(double[] samples, int sampleRate, int duration) {
        this.samples = samples;
        this.sampleRate = sampleRate;
        this.duration = duration;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the dimensions of the graph
        int width = getWidth();
        int height = getHeight();

        // Draw the x- and y-axes of the graph
        g.drawLine(0, height / 2, width, height / 2);
        g.drawLine(0, 0, 0, height);
        // Plot the sample points on the graph
        int x1, y1, x2, y2;
        x1 = 0;
        y1 = (int) (height / 2 - samples[0] * height / 2);
        for (int i = 1; i < samples.length; i++) {
            x2 = (int) (i * width / samples.length);
            y2 = (int) (height / 2 - samples[i] * height / 2);
            g.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }
    }
}
