import javax.swing.SwingUtilities;

public class Main{
    public static void main(String[] args) {
        if(args.length > 1){
            int n = Integer.parseInt(args[0]);
            FramePuzzle frame = new FramePuzzle(n);
            SwingUtilities.invokeLater(frame);
          } else if (args.length <= 1){
            System.out.println("Maaf ukurannya tidak boleh <= 1 !");
          } else {
            FramePuzzle frame = new FramePuzzle(4);
            SwingUtilities.invokeLater(frame);
          }
        
    }
}

