package net.avuna.rae;

import de.gurkenlabs.litiengine.gui.screens.Screen;
import net.avuna.rae.ui.MagicEightBall;

import java.awt.*;

public class MagicEightBallScreen extends Screen {

	private MagicEightBall ball;

	protected MagicEightBallScreen() {
		super("magic 8 ball");
	}

	@Override
	public void prepare() {
		super.prepare();
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, (int) getWidth(), (int) getHeight());
		super.render(g);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		ball = new MagicEightBall(400, 200, 500, 500);

		getComponents().add(ball);
	}
}
