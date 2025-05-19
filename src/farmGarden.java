import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class farmGarden {
    public static void main(String[] args) throws InterruptedException, AWTException {
        Robot robot = new Robot();
        String targetItemName = JOptionPane.showInputDialog(null, "Enter target item name: (wheat, carrot, potato): ");
        if (targetItemName == null || targetItemName.trim().isEmpty()) {
            System.out.println("No item entered. Exiting.");
            return;
        }
        for(int i = 0; i < 5; i++) {
            farm(robot, 1, "wheat");
        }
    }

    public static void farm(Robot robot, int layer, String crop) throws InterruptedException {
        int cropInt = 0;
        switch(crop) {
            case "carrot","potato" -> cropInt = 0;
            case "wheat" -> cropInt = 1;
            default -> {System.out.print("crop does not exist");
                        System.exit(0);}
        }
        waitUntilReady();
        warpGarden(robot);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        for (int i = layer; i <= 5; i++) {
            
            if ((i+cropInt) % 2 != 0) {
                walkLeft(robot);
            } else walkRight(robot);
        }
    }
    // self explanatory functions, one walks right for 2 minutes, the other right for 2 minutes, need to adjust that to your farm size
    public static void walkLeft(Robot robot) throws InterruptedException {
        robot.keyPress(KeyEvent.VK_A);
        Thread.sleep(120000);
        robot.keyRelease(KeyEvent.VK_A);
    }

    public static void walkRight(Robot robot) throws InterruptedException {
        robot.keyPress(KeyEvent.VK_D);
        Thread.sleep(120000);
        robot.keyRelease(KeyEvent.VK_D);
    }

    public static void waitUntilReady() throws InterruptedException {
        Thread.sleep(3000); // Script starts after waiting for 3 seconds so you can get ready
    }

    public static void warpGarden(Robot robot) { // types warp garden
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_T);
        pressSlash(robot);
        String warpGarden = "warp garden";
        for (int i = 0; i < warpGarden.length(); i++) {
            char c = warpGarden.charAt(i);
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);

            if (keyCode != KeyEvent.VK_UNDEFINED) {
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
            }
            robot.delay(50);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(50);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    public static void pressSlash(Robot robot) {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(50);
        robot.keyPress(KeyEvent.VK_7);
        robot.keyRelease(KeyEvent.VK_7);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }
}
