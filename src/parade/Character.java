/* 
 * Character
 * Copyright (C) 2013 Juhani Numminen
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

/**
 * Game character that is able to move with its own AI or user's input.
 */
class Character {
    
    private float speed;
    private float x;
    private float y;
    private Image img;
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
        x = 0;
        y = 0;
        this.img = img;
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
    public Character(float speed, float x, float y, Image img, int exp) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.img = img;
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
        img.draw(x, y);
    }
}
