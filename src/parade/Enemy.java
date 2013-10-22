/*
 * Enemy - the enemy types of Death And Glory
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

/**
 * The enemy types.
 */
public enum Enemy {

    /**
     * Orc, a weak green enemy.
     */
    ORC("Orc", 100f, 100f, 0),
    /**
     * Troll, watch out for these yellow monsters.
     */
    TROLL("Troll", 90f, 150f, 100),
    /**
     * Daemon, a satanic creature.
     */
    DAEMON("Daemon", 100f, 200f, 150);
    /** The user friedly name of this Enemy. */
    public final String name;
    /** The speed of this Enemy. */
    public final float speed;
    /** The initial health of this Enemy. */
    public final float health;
    /** The initial experience of this Enemy. */
    public final int exp;

    Enemy(String name, float speed, float health, int exp) {
        this.name = name;
        this.speed = speed;
        this.health = health;
        this.exp = exp;
    }
}
