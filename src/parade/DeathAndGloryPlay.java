/* 
 * DeathAndGloryPlay
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

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DeathAndGloryPlay extends BasicGameState {

    private DeathAndGloryGame.State state;
    private Character player;
    private ArrayList<Character> enemies;
    private Image gameBack;

    private MessageBox msgBox;

    public DeathAndGloryPlay(DeathAndGloryGame.State state) {
        this.state = state;
    }

    @Override
    public int getID() {
        return state.ordinal();
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        player = new Character("Hero", 100, 355, 525,
                new Image("assets/img/hero.png"), 100, 10);
        enemies = new ArrayList<Character>();
        enemies.add(new Character("Orc", 100, gc.getWidth() / 2, 20,
                new Image("assets/img/orc.png"), 100, 0));
        gameBack = new Image("assets/img/back.png");

        msgBox = new MessageBox(0, gc.getHeight() - 110);
        msgBox.addMessage("Welcome to Dungeon.");

        gc.setShowFPS(false);
        gc.setTargetFrameRate(60);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        gameBack.draw(0, 0);
        player.render(g);
        for (Character enemy : enemies) {
            enemy.render(g);
        }
        msgBox.render(g);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int msSinceLastUpdate) throws SlickException {
        player.updateArrowKeys(gc, msSinceLastUpdate);
        for (Character enemy : enemies) {
            enemy.updateAI(gc, msSinceLastUpdate);
        }

        // check for collisions
        Input input  = gc.getInput();
        Rectangle playerRectangle = player.getCollisionRect();
        
        ArrayList<Character> killedEnemies = new ArrayList<Character>();
        
        for (Character enemy : enemies) {
            if (playerRectangle.intersects(enemy.getCollisionRect())) {
                if (input.isKeyPressed(Input.KEY_SPACE)) {
                    msgBox.addMessage("Battle begins.");
                    player.battle(enemy);
                    
                    if (!enemy.isAlive()) {
                        msgBox.addMessage("You have won " + enemy + ".");
                        killedEnemies.add(enemy);
                    }
                }
            }
        }
        
        enemies.removeAll(killedEnemies);

        Random rnd = new Random();
        if (enemies.size() < 5 && rnd.nextFloat() > 0.99) {
            msgBox.addMessage("A wild Orc appears.");
            enemies.add(new Character("Orc", 100, gc.getWidth() / 2 + (rnd.nextInt(600) - 300), rnd.nextInt(200),
                new Image("assets/img/orc.png"), 100, 0));
        }
    }
}
