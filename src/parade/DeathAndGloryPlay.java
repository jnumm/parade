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

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DeathAndGloryPlay extends BasicGameState {

    private DeathAndGloryGame.State state;
    private Character player;
    private Character enemy;
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
        player = new Character(new Image("assets/img/hero.png"));
        enemy = new Character(100.0f, gc.getWidth() / 2.0f, 20.0f,
                new Image("assets/img/orc.png"), 100, 0);
        gameBack = new Image("assets/img/back.png");

        msgBox = new MessageBox(0, gc.getHeight() - 110);
        msgBox.addMessage("Welcome to Dungeon!");

        gc.setShowFPS(false);
        gc.setTargetFrameRate(60);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        gameBack.draw(0, 0);
        player.render(g);
        enemy.render(g);
        msgBox.render(g);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int msSinceLastUpdate) throws SlickException {
        player.updateArrowKeys(gc, msSinceLastUpdate);
        enemy.updateAI(gc, msSinceLastUpdate);

        // check for collisions
        if (player.getCollisionRect().intersects(enemy.getCollisionRect())) {
            Input input  = gc.getInput();
            System.out.println("osuu!!");
            if (input.isKeyPressed(Input.KEY_SPACE)) {
                msgBox.addMessage("Battle begins!");
                player.battle(enemy);
            }
        }

    }
}
