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
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * The MessageBox shows information and messages.
 */
public class MessageBox {
    private int x;
    private int y;
    private ArrayList<String> messages;

    public MessageBox(int x, int y) {
        this.x = x;
        this.y = y;
        messages = new ArrayList<String>();
    }

    public void addMessage(String message) {
        messages.add(message);
        if (messages.size() > 5) {
            messages.remove(0);
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(0x2B2825));
        g.fillRect(x, y, 300, 110);
        g.setColor(Color.white);
        
        int tempY = y + 10;
        for (String message : messages) {
            g.drawString(message, x + 10, tempY);
            tempY += 20;
        }
    }
}
