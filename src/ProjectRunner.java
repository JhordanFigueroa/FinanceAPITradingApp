/**
 * This is the Project Runner class, this is where the main method for the project is stored.
 *
 * @author Alex llgenfritz, Chris Payne, and Jhordan Figueroa
 */

public class ProjectRunner {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserInterface.createAndShowGUI();
            }
        });
    }
}
