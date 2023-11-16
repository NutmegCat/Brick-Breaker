import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.*;

public class BrickBreakerGame extends JFrame implements ActionListener, KeyListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    private Timer timer;
    private int ballX, ballY, ballSpeedX, ballSpeedY;
    private int paddleX, paddleSpeed;
    private boolean[] bricks;
    private int score;

    public BrickBreakerGame () {
        setTitle("Brick Breaker");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        bricks = new boolean[5 * 10];
        ballX = WIDTH / 2;
        ballY = HEIGHT / 2;
        ballSpeedX = 2;
        ballSpeedY = 3;
        paddleX = WIDTH / 2 - 50;
        paddleSpeed = 5;
        score = 0;

        timer = new Timer(10, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }
}