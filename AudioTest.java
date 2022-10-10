
public class AudioTest {
    private static int[] data;
    public static int[] calculateData(int range){
        data = new int[range];
        for(int i = 0; i < range ; i++){
            data[i] = (int) Math.sin((double)i); //this is an example, use your mathematical function here
        }
        return data;
    }
    
    public static void main(String[] args) {
        int range = 100; //adjust as you wish
        FuncAnimation plot = new FuncAnimation();
        plot.coord = calculateData(range);
    }
}