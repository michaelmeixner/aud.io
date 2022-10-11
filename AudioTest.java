import java.time.*;
public class AudioTest {
    private static int[] data;
    private static int[] time;

    public static void makeWave(int range){
        data = new int[range];
        time = new int[range];
        for(int i = 0; i < range ; i++) {
            for(int j = 0; j < time.length; j++) {
                data[i] = (int) Math.sin((double)i);
                time[j] = j;
            }
        }
    }
    
    public static void main(String[] args) {
        int range = 100;
        FuncAnimation plot = new FuncAnimation();
        makeWave(range);
        plot.display();
    }
}