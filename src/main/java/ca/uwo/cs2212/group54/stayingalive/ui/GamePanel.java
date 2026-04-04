package ca.uwo.cs2212.group54.stayingalive.ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import ca.uwo.cs2212.group54.stayingalive.game.Enemies.Enemy;
import ca.uwo.cs2212.group54.stayingalive.game.Gameplay;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer gameLoop;
    private long lastTime = System.nanoTime();

    // Player position
    private int playerX = Gameplay.getPlayerX();
    private int playerY = Gameplay.getPlayerY();

    // Color
    private static final Color backgroundPurple = new Color(106, 69, 156);
    private static final int borderSize = 20;

    // Gameplay Related
    private static boolean running;
    private Gameplay gameplay;

    public GamePanel(Gameplay gameplay) {
        setFocusable(true);
        addKeyListener(this);

        this.gameplay = gameplay;

        // Game loop (runs ~60 FPS)
        gameLoop = new Timer(16, this);
        gameLoop.start();
        running = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background
        g.setColor(backgroundPurple);
        g.fillRect(0, 0, getWidth(), getHeight());
        GradientPaint gp = new GradientPaint(0, 0, GameplayScreen.getBackgroundClr1(), 
        0, getHeight(), GameplayScreen.getBackgroundClr2());
        g.fillRect(20, 20, getWidth() - 20, getHeight() - 20);

        g2d.setPaint(gp);
        g2d.fillRect(borderSize, borderSize, getWidth() - borderSize * 2, getHeight() - borderSize * 2);

        // Player
        g.setColor(Color.GREEN);
        g.fillRect(playerX, playerY, 50, 50);

        // Example enemy
        g.setColor(Color.RED);
        List<Enemy> active = gameplay.getActiveEnemies();
        for (Enemy enemy: active) {
            System.out.println(enemy.getPositionX() + " " + enemy.getPositionY());
            //g.fillRect(enemy.getPositionX(), enemy.getPositionY(), 40, 40);
            JLabel label = enemy.getSprite().getImage();
            Icon icon = label.getIcon();
            Image image = ((ImageIcon) icon).getImage();
            
            g.drawImage(image, enemy.getSprite().getX(), enemy.getSprite().getY(), 200, 200, null);
            g.drawLine(playerX,playerY,enemy.getSprite().getX(),enemy.getSprite().getY());
            //g.drawImage(image, enemy.getPositionX(), enemy.getPositionY(), 200, 200, null);
            /*if (enemy.getPositionX() < playerX) {
                g.drawImage(image, enemy.getPositionX(), enemy.getPositionY(), 200, 200, null);
            } else {
                g.drawImage(image, enemy.getPositionX(), enemy.getPositionY(), -200, 200, null);
            }*/
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long now = System.nanoTime();
        float deltaTime = (now - lastTime) / 100_000_000.0f;
        lastTime = now;
        updateGame(deltaTime);
        repaint();
    }

    /**
     * All the updates that take place per frame.
     */
    private void updateGame(float deltaTime) {
        gameplay.update(deltaTime);
    }

    // Key controls
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            /*case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_A -> left = true;
            case KeyEvent.VK_D -> right = true;*/
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            /*case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;*/
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static boolean getRunState() { return running; }
}