/* 
 * DeathAndGloryPlay
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
    private Image imgOrc;
    private Image imgTroll;
    private Image gameBack;

    private MessageBox msgBox;

    private Enemy enemyType;

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
        imgOrc = new Image("assets/img/orc.png");
        imgTroll = new Image("assets/img/troll.png");
        enemies.add(new Character(Enemy.ORC, imgOrc));
        gameBack = new Image("assets/img/back.png");

        msgBox = new MessageBox(0, gc.getHeight() - 110);
        msgBox.addMessage("Welcome to Dungeon.");

        enemyType = Enemy.ORC;

        gc.setShowFPS(false);
        gc.setTargetFrameRate(60);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
        gameBack.draw(0, 0);
        player.render(g);
        for (Character enemy : enemies) {
            enemy.render(g);
        }
        msgBox.render(g);
        g.drawString("Exp: " + player.getExp(), gc.getWidth() - 110, 20);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int msSinceLastUpdate)
            throws SlickException {
        if (!player.isAlive()) {
            msgBox.addMessage("Game over. You have died.");
            msgBox.addMessage("Better luck next time!");
        }

        player.updateArrowKeys(gc, msSinceLastUpdate);
        for (Character enemy : enemies) {
            enemy.updateAI(gc, msSinceLastUpdate);
        }

        // check for collisions
        Input input = gc.getInput();
        Rectangle playerRectangle = player.getCollisionRect();

        ArrayList<Character> killedEnemies = new ArrayList<Character>();

        for (Character enemy : enemies) {
            if (!enemy.isAlive()) {
                msgBox.addMessage("You have won " + enemy + ".");
                killedEnemies.add(enemy);
            }

            if (playerRectangle.intersects(enemy.getCollisionRect())) {
                if (input.isKeyDown(Input.KEY_SPACE) && !player.isInBattle()) {
                    msgBox.addMessage("Battle begins.");
                    player.startBattle(enemy);
                }
            }
        }

        enemies.removeAll(killedEnemies);

        if (enemies.size() < 5 && Parade.r.nextFloat() > 0.99) {
            if (enemyType == Enemy.ORC) {
                msgBox.addMessage("A wild Orc appears.");
                enemies.add(new Character(Enemy.ORC, imgOrc));
            } else if (enemyType == Enemy.TROLL) {
                msgBox.addMessage("A wild Troll appears.");
                enemies.add(new Character(Enemy.TROLL, imgTroll));
                enemies.add(new Character(Enemy.ORC, imgOrc));
                enemies.add(new Character(Enemy.ORC, imgOrc));
            }
        }

        if (player.getExp() >= 100) {
            enemyType = Enemy.TROLL;
        }
    }
}
