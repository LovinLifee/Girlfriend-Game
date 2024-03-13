package net.avuna.rae.levels;

import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import net.avuna.rae.ui.Button;

import java.awt.*;
import java.awt.geom.Point2D;

public class WinningScreen extends Screen {

	private final String WINNING_MESSAGE = "Congratulations, you've won! <3";
	private Button continueButton;

	public WinningScreen() {
		super("winning-screen");
	}

	@Override
	public void prepare() {
		super.prepare();
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 30F));
		TextRenderer.renderRotated(g, WINNING_MESSAGE, new Point2D.Double(getCenterX() - (TextRenderer.getWidth(g, WINNING_MESSAGE) / 2), 150), 7D);
		super.render(g);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		continueButton = new Button(400, 500, 300, 100, Color.WHITE, Color.WHITE);
		continueButton.setText("Continue");
		super.getComponents().add(continueButton);
	}
}
