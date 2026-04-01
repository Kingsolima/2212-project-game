package game.Enemies;

import game.Gameplay;
import sprites.Sprite;

/**
 * Enemy class
 * 
 * @author Malik Alghneimin
 * 
 */
public class Enemy {
    private String[] words;
    private Enemy_Attribute attribute;
    private Sprite sprite;
    private int damage;
    private float speed;
    private int weight;
    private int score;

    protected boolean timeStopped; // How do we implement the time stop???

    /**
     * Constructor for Enemy class. 
     * 
     * @param words Array of words for the enemy
     * @param attribute Attribute of the enemy
     * @param sprite Sprite of the enemy
     */
    public Enemy(String[] words, Enemy_Attribute attribute, Sprite sprite) {
        this.words = words; // The first word is the one that needs to be typed.
        this.attribute = attribute;
        this.sprite = sprite;

        switch (attribute) {
            case NORMAL: {
                this.damage = 1;
                this.speed = 1.0f;
                this.weight = 1;
                this.score = 10;
            }
            case HAS_HEART: {
                this.damage = 1;
                this.speed = 1.0f;
                this.weight = 1;
                this.score = 25;
            }
            case BIG: {
                this.damage = 2;
                this.speed = 0.75f;
                this.weight = 5;
                this.score = 50;
            }
            case BOSS: {
                this.damage = 2;
                this.speed = 0.5f;
                this.weight = 10;
                this.score = 100;
            }
        }
    }

    /**
     * Gets the damage of the enemy.
     * 
     * @return The damage of the enemy.
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Moves the enemy.
     */
    public void move() {
        // TODO: Implement move function
    }

    /**
     * Checks if the enemy has contacted the player.
     * 
     * @param player The player to check for contact
     * @return True if the enemy has contacted the player, false otherwise
     */
    public boolean contact(Gameplay player) {
        // Move contact logic to Gameplay?
        return false;
    }
    
    /**
     * Updates the words of the enemy.
     */
    public void updateWords() {
        // Either eliminate current word or 
    }
}
