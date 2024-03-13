package net.avuna.rae.levels;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import net.avuna.rae.ui.Button;

import java.awt.*;
import java.awt.geom.Point2D;

public class Level5 extends Screen {

	private Button yesButton;
	private Button noButton;

	private final String MESSAGE = "Would you like to be my girlfriend? <3";

	public Level5() {
		super("level-5");
	}

	@Override
	public void prepare() {
		super.prepare();
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 30F));
		TextRenderer.renderRotated(g, MESSAGE, new Point2D.Double(getCenterX() - (TextRenderer.getWidth(g, MESSAGE) / 2), 150), 7D);
		super.render(g);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		yesButton = new Button(400, 500, 150, 75, Color.GREEN, Color.WHITE);
		yesButton.setText("Yes");
		noButton = new Button(700, 500, 150, 75, Color.RED, Color.WHITE);
		noButton.setText("No");
		super.getComponents().add(yesButton);
		super.getComponents().add(noButton);

		noButton.onHovered(e -> {
			int maxX = (int) (getWidth() - noButton.getWidth());
			int maxY = (int) (getHeight() - noButton.getHeight());
			int randomX = Game.random().nextInt(maxX + 1);
			int randomY = Game.random().nextInt(maxY + 1);
			noButton.setX(randomX);
			noButton.setY(randomY);
		});
	}
}
