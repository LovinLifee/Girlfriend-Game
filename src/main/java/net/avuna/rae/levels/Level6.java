package net.avuna.rae.levels;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.Mouse;
import net.avuna.rae.ui.Button;

import java.awt.*;
import java.awt.geom.Point2D;

public class Level6 extends Screen {

	private Button yesButton;
	private Button yesButton2;
	private Button noButton;

	private final String MESSAGE = "Are you SURE????";

	public Level6() {
		super("level-6");
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

		yesButton2 = new Button(400, 500, 150, 75, Color.GREEN, Color.WHITE);
		yesButton2.setText("Yes");
		yesButton2.setVisible(false);

		noButton = new Button(700, 500, 150, 75, Color.RED, Color.WHITE);
		noButton.setText("No");

		noButton.onClicked(e -> {
			int x = (int) noButton.getX();
			int y = (int) noButton.getY();
			yesButton2.setX(x);
			yesButton2.setY(y);
			yesButton2.setVisible(true);
			super.getComponents().remove(noButton);
		});
		super.getComponents().add(yesButton);
		super.getComponents().add(yesButton2);
		super.getComponents().add(noButton);

		Game.loop().attach(() -> {
			if(noButton == null) {
				return;
			}
			int mouseX = (int) Input.mouse().getLocation().getX();
			int mouseY = (int) Input.mouse().getLocation().getY();
			int entityX = (int) noButton.getX();
			int entityY = (int) noButton.getY();
			int entityWidth = (int) noButton.getWidth();
			int entityHeight = (int) noButton.getHeight();
			int containerWidth = (int) getWidth();
			int containerHeight = (int) getHeight();
			if (mouseX != -1 && mouseY != -1) { // Ensure mouse is within panel
				double dx = entityX - mouseX;
				double dy = entityY - mouseY;
				double distance = Math.sqrt(dx * dx + dy * dy);

				if (distance < 200) { // Adjust this threshold for how far to stay away
					double angle = Math.atan2(dy, dx);
					entityX += Math.cos(angle) * 8; // Adjust speed of avoidance
					entityY += Math.sin(angle) * 8; // Adjust speed of avoidance
				}
			}

			// Ensure the entity stays within the container
			if (entityX < 0) entityX = 0;
			if (entityY < 0) entityY = 0;
			if (entityX + entityWidth > containerWidth) {
				entityX = containerWidth - entityWidth;
			}
			if (entityY + entityHeight > containerHeight){
				entityY = containerHeight - entityHeight;
			}
			noButton.setX(entityX);
			noButton.setY(entityY);
		});
	}
}
