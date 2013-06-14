/* 
 * DeathAndGloryMenu
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
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        menuBack = new Image("assets/img/menu.png");
        sound = new Sound("assets/audio/boss_theme.ogg") ;
        gameContainer.setShowFPS(false);
        gameContainer.setTargetFrameRate(30);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        menuBack.draw(0, 0);
        sound.loop(1.0f, 0.5f);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int msFromLastChange) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            stateBasedGame.enterState(DeathAndGloryGame.State.PLAY.ordinal());
        }
    }
}
