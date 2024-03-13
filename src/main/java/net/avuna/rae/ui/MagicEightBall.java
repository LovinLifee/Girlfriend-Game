package net.avuna.rae.ui;

import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.ImageComponent;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MagicEightBall extends ImageComponent {

	private BufferedImage eightBallImage;

	private String[] good = {"Slay girl boss"};
	private String[] bad = {"On Midnas life you think that's a good idea?"};

	public MagicEightBall(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public void prepare() {
		// This method will be called right before the screen is displayed
		super.prepare();
		this.eightBallImage = Resources.images().get("8ball.png");
	}

	@Override
	public void render(Graphics2D g) {
		// This method is called on every tick and lets you draw shapes, text, images etc. explicitly.
		// Calling super.render(g) also renders all GuiComponents in this.getComponents()
		super.render(g);
		ImageRenderer.render(g, eightBallImage, getX(), getY());
		Font font = g.getFont().deriveFont(Font.BOLD, 15f);
		g.setFont(font);
		g.setColor(Color.WHITE);
		TextRenderer.enableTextAntiAliasing(g);
		TextRenderer.render(g, getText(), getBoundingBox(), Align.CENTER, Valign.MIDDLE, 0, -30, true);
	}

	@Override
	protected void initializeComponents() {
		// This method is called once by the Screen's constructor. Use it to initialize the GuiComponents that will be contained by this screen.
		// Don't forget to call this.getComponents().add(GuiComponent c) so that the components will actually be rendered.
		setText("");
		super.initializeComponents();
		super.onClicked(e -> {
			setText("");
			Game.loop().attach(oscillation);
			Game.loop().perform(1500, () -> {
				Random random = ThreadLocalRandom.current();
				boolean flip = random.nextBoolean();
				String answer = flip ? good[random.nextInt(good.length)] : bad[random.nextInt(bad.length)];
				setText(answer);
				Game.loop().detach(oscillation);
			});
		});
	}

	private final IUpdateable oscillation = () -> {
		final int SHAKE_DISTANCE = 15;
		int xOffset = (int) (Math.random() * SHAKE_DISTANCE * 2) - SHAKE_DISTANCE;
		int yOffset = (int) (Math.random() * SHAKE_DISTANCE * 2) - SHAKE_DISTANCE;
		setX(getX() + xOffset);
		setY(getY() + yOffset);
	};
}
