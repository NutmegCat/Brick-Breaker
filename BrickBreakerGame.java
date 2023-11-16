import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.*;

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

    public void actionPerformed(ActionEvent e) {
        moveBall();
        movePaddle();
        checkCollision();
        repaint();
    }

    private void moveBall() {
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        if (ballX <= 0 || ballY >= WIDTH - 20) {
            ballSpeedX = -ballSpeedX;
        }

        if (ballY <= 0) {
            ballSpeedY = -ballSpeedY;
        }

        if (ballY >= HEIGHT - 20) {
            // Game over
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over!\nYour score: " + score);
            System.exit(0);
        }
    }
    private void movePaddle() {
        if (paddleX < 0) {
            paddleX = 0;
        } else if (paddleX > WIDTH - 100) {
            paddleX = WIDTH - 100;
        }
    }

    private void checkCollision() {
        Rectangle ballRect = new Rectangle(ballX, ballY, 20, 20);
        Rectangle paddleRect = new Rectangle(paddleX, HEIGHT - 30, 100, 20);

        if (ballRect.intersects(paddleRect)) {
            ballSpeedY = -ballSpeedY;
        }

        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i]) {
                Rectangle brickRect = new Rectangle((i % 10) * 40, (i / 10) * 20, 40, 20);
                if (ballRect.intersects(brickRect)) {
                    ballSpeedY = -ballSpeedY;
                    bricks[i] = false;
                    score += 10;

                    // Check for victory
                    boolean win = true;
                    for (boolean brick : bricks) {
                        if (brick) {
                            win = false;
                            break;
                        }
                    }

                    if (win) {
                        timer.stop();
                        JOptionPane.showMessageDialog(this, "You Win!\nYour score: " + score);
                        System.exit(0);
                    }
                }
            }
        }
    }
}