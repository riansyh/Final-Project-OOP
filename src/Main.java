import javax.swing.SwingUtilities;

public class Main{
    public static void main(String[] args) {
        if(args.length != 0){
            int n = Integer.parseInt(args[0]);
            if(n <= 1){
              System.out.println("Size tidak boleh <= 1 !");
            } else {
            FramePuzzle frame = new FramePuzzle(n);
            SwingUtilities.invokeLater(frame);
            }
          } else {
            FramePuzzle frame = new FramePuzzle(4);
            SwingUtilities.invokeLater(frame);
          }
        
    }
}

