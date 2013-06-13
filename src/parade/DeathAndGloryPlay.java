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

import java.awt.geom.Point2D;
import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DeathAndGloryPlay extends BasicGameState {

    private DeathAndGloryGame.State state;
    private Sound backgroundMusic;
    private Sound upSound;
    private Sound downSound;
    private Animation currentAnimation;
    private Animation walkAnimation;
    private Animation runAnimation;
    private Animation spaceshipAnimation;
    private float speed;
    private float x;
    private float y;
    private int points = 0;
    private ArrayList<Point2D.Float> spaceshipLocations;

    public DeathAndGloryPlay(DeathAndGloryGame.State state) {
        this.state = state;
    }

    @Override
    public int getID() {
        return state.ordinal();
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.setTargetFrameRate(60);

        backgroundMusic = new Sound("assets/audio/out-there.ogg");
        backgroundMusic.loop();

        upSound = new Sound("assets/audio/jump_03.wav");
        downSound = new Sound("assets/audio/jump_01.wav");

        SpriteSheet robotSpriteSheet = new SpriteSheet("assets/img/robot.png", 64, 68);
        walkAnimation = new Animation(robotSpriteSheet, 0, 0, 3, 0, false, 300, true);
        runAnimation = new Animation(robotSpriteSheet, 1, 1, 3, 1, false, 100, true);

        speed = 60;
        x = 100;
        y = 250;

        SpriteSheet spaceshipSpriteSheet = new SpriteSheet("assets/img/spaceship.png", 200, 110);
        spaceshipSpriteSheet = new SpriteSheet(spaceshipSpriteSheet.getScaledCopy(0.3f), 60, 33);
        spaceshipAnimation = new Animation(spaceshipSpriteSheet, 0, 0, 4, 0, false, 100, true);

        spaceshipLocations = new ArrayList<Point2D.Float>();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawString("Points: " + points, gameContainer.getWidth() / 4, gameContainer.getHeight() - 20);
        points++;
        currentAnimation.draw(x, y);


        for (Point2D.Float location : spaceshipLocations) {
            spaceshipAnimation.draw(location.x, location.y);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int msFromLastChange) throws SlickException {
        // handle input
        Input input = gameContainer.getInput();
        if (input.isKeyPressed(Input.KEY_UP)) {
            if (y > 200) {
                upSound.play();
                y -= 50;
            }
        }


        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (y < 300) {
                downSound.play();
                y += 50;
            }
        }

        if (input.isKeyDown(Input.KEY_SPACE)) {
            currentAnimation = walkAnimation;
            speed = 20;
        } else {
            currentAnimation = runAnimation;
            if (speed < 10000) {
                speed++;
            }
        }

        // check for collisions
        Rectangle roboRect = new Rectangle(x, y, 50, 40);
        for (Point2D.Float location : spaceshipLocations) {
            if (roboRect.contains(location.x, location.y)) {
                speed = 20;
                points = points / 5;
            }
        }


        // move
        float diff = speed * msFromLastChange / 1000;
        ArrayList<Point2D> toRemove = new ArrayList<Point2D>();
        for (Point2D location : spaceshipLocations) {
            location.setLocation(location.getX() - diff, location.getY());

            if (location.getX() < -100) {
                toRemove.add(location);
            }
        }

        spaceshipLocations.removeAll(toRemove);

        // create new spaceships
        if (Math.random() > 0.988) {
            double rnd = Math.random();
            if (rnd < 0.33) {
                spaceshipLocations.add(new Point2D.Float(600, 210));
            } else if (rnd < 0.66) {
                spaceshipLocations.add(new Point2D.Float(600, 260));
            } else {
                spaceshipLocations.add(new Point2D.Float(600, 310));
            }
        }
    }
}
