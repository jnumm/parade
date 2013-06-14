/* 
 * Character
 * Copyright (C) 2013 Juhani Numminen
 * Copyright (C) 2013 Tuomas Numminen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package parade;

import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

/**
 * Game character that is able to move with its own AI or user's input.
 */
class Character {
    
    private float speed;
    private float x;
    private float y;
    private Image img;
    private float health;
    private int exp;
    
    private int aiCounter;
    private float aiMoveX;
    private float aiMoveY;

    /**
     * Creates a new Character with the specified image.
     * @param img character's image
     */
    public Character(Image img) {
        speed = 200;
        x = 355;
        y = 525;
        this.img = img;
        health = 100;
        exp = 0;
        aiCounter = 0;
    }
    
    /**
     * Creates a new Character specifying all of its properties.
     * @param speed movement speed per second
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param img character's image
     * @param exp character's experience points
     */
    public Character(float speed, float x, float y, Image img, float health, int exp) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.img = img;
        this.health = health;
        this.exp = exp;
        this.aiCounter = 0;
    }

    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }

    /**
     * Returns a rectangle for collision check.
     * @return Rectangle of Character's current position
     */
    public Rectangle getCollisionRect() {
        return new Rectangle(x, y, 64, 64);
    }

    /**
     * Starts battle.
     * @param opponent character to fight with
     */
    public void battle(Character opponent) {
        while (this.isAlive() && opponent.isAlive()) {
            opponent.changeHealth(this.getHit());
            this.changeHealth(opponent.getHit());
        }

        if (this.isAlive()) {
            this.addExp(opponent.getExp());
        } else if (opponent.isAlive()) {
            opponent.addExp(this.getExp());
        }
    }

    public boolean isAlive() {
        if (health > 0) {
            return true;
        }

        return false;
    }

    /**
     * Gets the hit this character will cause to others.
     * @return hit
     */
    public float getHit() {
        exp++;
        return 2.0f * exp;
    }

    /**
     * Changes the character's health.
     * @param diff how much to add or remove
     */
    public void changeHealth(float diff) {
        health += diff;
        if (health > 0.1) {
            health = 0;
        }
    }

    /**
     * Gets the experience to add when another character defeats this one.
     * @return experience
     */
    public int getExp() {
        return (int) 1.5 * exp;
    }

    /**
     * Changes the character's experience.
     * @param diff how much to add
     */
    public void addExp(int diff) {
        exp += diff;
    }

    /**
     * Moves the character, if arrow keys are pressed.
     *
     * @param gc GameContainer to get input from
     * @param msSinceLastUpdate for calculations
     */
    public void updateArrowKeys(GameContainer gc, int msSinceLastUpdate) {
        Input input = gc.getInput();
        float diff = speed * msSinceLastUpdate / 1000;

        if (input.isKeyDown(Input.KEY_UP)) {
            y -= diff;
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            y += diff;
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            x -= diff;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            x += diff;
        }        
    }

    /**
     * Moves the character automatically, without any user input.
     * @param gc GameContainer to get screen size from
     * @param msSinceLastUpdate for calculations
     */
    public void updateAI(GameContainer gc, int msSinceLastUpdate) {
        if (aiCounter > 30) {
            Random rnd = new Random();
            aiCounter = rnd.nextInt(10);
            aiMoveX = (rnd.nextFloat() - 0.5f) * 11;
            aiMoveY = (rnd.nextFloat() - 0.5f) * 10;
        }
        
        x += aiMoveX;
        y += aiMoveY;
        
        aiMoveX /= 2;
        aiMoveY /= 2;
        
        if (x < 0) {
            x = 0;
        } else if (x > gc.getWidth() - img.getWidth()) {
            x = gc.getWidth() - img.getWidth();
        }
        
        if (y < 0) {
            y = 0;
        } else if (y > gc.getHeight() - img.getHeight()) {
            y = gc.getHeight() - img.getHeight();
        }
        
        aiCounter++;
    }
    
    /**
     * Renders the character image.
     * @param g
     */
    public void render(Graphics g) {
        g.drawString(String.format("%.1f", health), x - 5, y - 5);
        img.draw(x, y);
    }
}
