/* 
 * ChristmasTree - prints a cool christmas tree
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

/**
 * ChristmasTree provides utilities for creating ASCII art christmas trees.
 */
public class ChristmasTree {

    private static String addStars(int number) {
        String ret = "";
        for (; number > 0; number--) {
            ret += "*";
        }
        return ret + "\n";
    }

    private static String addSpaces(int number) {
        String ret = "";
        for (; number > 0; number--) {
            ret += " ";
        }
        return ret;
    }

    /**
     * Creates a string containing a an isosceles triangle and a rectangle, a
     * christmas tree.
     * @param height the height including base.
     * @return a string containing the tree.
     */
    public static String christmasTree(int height) {
        String tree = "";
        height -= 2;

        for (int i = 1; i <= height; i++) {
            tree += addSpaces(height - i);
            tree += addStars(2 * i - 1);
        }

        tree += addSpaces(height - 2);
        tree += addStars(3);

        tree += addSpaces(height - 2);
        tree += addStars(3);

        return tree;
    }
}
