/* 
 * DeathAndGloryGame
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
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class DeathAndGloryGame extends StateBasedGame {

    /**
     * Game state.
     */
    public enum State {

        /**
         * The game is in menu.
         */
        MENU,
        /**
         * The game is running.
         */
        PLAY,
        /**
         * The game is in credits menu.
         */
        CREDITS;
    }

    public DeathAndGloryGame() {
        super("Death And Glory");

        addState(new DeathAndGloryMenu(State.MENU));
        addState(new DeathAndGloryPlay(State.PLAY));
        addState(new DeathAndGloryCredits(State.CREDITS));
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        for (State state : State.values()) {
            getState(state.ordinal()).init(gameContainer, this);
        }

        this.enterState(State.MENU.ordinal());
    }
}
