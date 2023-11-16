import javax.swing.*;

public class BrickBreakerGame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    private Timer timer;
    private int ballX, ballY, ballSpeedX, ballSpeedY;
    private int paddleX, paddleY;
    private boolean[] bricks;
    private int score;
}