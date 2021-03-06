/* 
 * DeathAndGloryMenu
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

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DeathAndGloryMenu extends BasicGameState {

    private DeathAndGloryGame.State state;
    private Image menuBack;
    private Sound sound;

    public DeathAndGloryMenu(DeathAndGloryGame.State state) {
        this.state = state;
    }

    @Override
    public int getID() {
        return state.ordinal();
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        menuBack = new Image("assets/img/menu.png");
        sound = new Sound("assets/audio/boss_theme.ogg") ;
        gc.setShowFPS(false);
        gc.setTargetFrameRate(30);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
        menuBack.draw(0, 0);
        sound.loop(1.0f, 0.5f);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int msFromLastChange)
            throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            game.enterState(DeathAndGloryGame.State.PLAY.ordinal());
        } else if (input.isKeyPressed(Input.KEY_C)) {
            game.enterState(DeathAndGloryGame.State.CREDITS.ordinal());
        }
    }
}
