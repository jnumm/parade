/* 
 * DeathAndGloryCredits
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
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DeathAndGloryCredits extends BasicGameState {

    static private String[][] credits = {
        {"Programming and Game Design", "Juhani Numminen"},
        {"Programming, Game Design & Graphics", "Tuomas Numminen"},
        {"Music", "\"Boss Theme\" by Remaxim"},
        {"", "\"Safe Room Theme\" by Remaxim"}
    };
    private DeathAndGloryGame.State state;
    private Image bgImage;
    private Sound sound;
    private int x;
    private float y;

    public DeathAndGloryCredits(DeathAndGloryGame.State state) {
        this.state = state;
    }

    @Override
    public int getID() {
        return state.ordinal();
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        bgImage = new Image("assets/img/menu.png");
        sound = new Sound("assets/audio/safe_room_theme.ogg");
        
        x = 200;
        y = gc.getHeight();
        
        gc.setShowFPS(false);
        gc.setTargetFrameRate(30);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        float tempY = y;
        sound.loop(1.0f, 0.5f);
        bgImage.draw(0, 0);
        for (String[] creditpair : credits) {
            g.drawString(creditpair[0], x, tempY);
            tempY += 30;
            if (creditpair.length == 2) {
                g.drawString(creditpair[1], x + 15, tempY);
                tempY += 60;
            } else {
                //tempY += 20;
            }
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int msSinceLastUpdate) throws SlickException {
        float diff = 50 * msSinceLastUpdate / 1000;
        y -= diff;

        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            game.enterState(DeathAndGloryGame.State.PLAY.ordinal());
            sound.stop();
        } if (input.isKeyPressed(Input.KEY_C)) {
            game.enterState(DeathAndGloryGame.State.MENU.ordinal());
            sound.stop();
        }
    }
}
