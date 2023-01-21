// Orban Abel, 621
package collection.components;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

// a custom button so I don't have to style them
public class SlrButton extends JButton {
    public SlrButton(String title) {
        setText(title);
        setFocusPainted(false);
        setFont(new Font("Arial", 0, 20));
        setMaximumSize(new Dimension(300, 40));
        setBackground(new Color(0, 20, 40));
        setForeground(new Color(200, 200, 200));
    }
}
