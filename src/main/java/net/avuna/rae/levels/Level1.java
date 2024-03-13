package net.avuna.rae.levels;

import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.input.Input;

import java.awt.*;
import java.awt.event.KeyAdapter;
import static java.awt.event.KeyEvent.*;

import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;

public class Level1 extends Screen {

	private final int MIN_DIAMETER = 50;
	private final int MAX_DIAMETER = 200;
	private final int SHRINK_SPEED = 1;
	private final int GROW_SPEED = 8;

	private int diameter = MIN_DIAMETER;


	private int konamiIndex = 0;
	private int[] konamiCode = {VK_UP, VK_UP, VK_DOWN, VK_DOWN, VK_LEFT, VK_RIGHT,VK_LEFT, VK_RIGHT, VK_B, VK_A, VK_ENTER};

	public Level1() {
		super("level-1");
	}

	@Override
	public void prepare() {
		super.prepare();
	}

	@Override
	public void render(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 40F));
		TextRenderer.render(g, "If she wanted to, she would. (click real fast)", Align.CENTER, Valign.TOP, 0, 150);
		Path2D expectedHeartPath = createHeartPath((int) getCenterX() - (MAX_DIAMETER / 2), (int) getCenterY() - (MAX_DIAMETER / 2), MAX_DIAMETER, MAX_DIAMETER);
		g.setColor(Color.WHITE);
		g.draw(expectedHeartPath);
		Path2D heartPath = createHeartPath((int) getCenterX() - (diameter / 2), (int) getCenterY() - (diameter / 2), diameter, diameter);
		g.setColor(Color.RED);
		g.fill(heartPath);
		super.render(g);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		IUpdateable shrink = () -> {
			diameter = Math.max(MIN_DIAMETER, diameter - SHRINK_SPEED);
		};
		super.onClicked(e -> {
			diameter += GROW_SPEED;
			if(diameter >= MAX_DIAMETER) {
				Game.loop().detach(shrink);
			}
		});
		Input.keyboard().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == konamiCode[konamiIndex]) {
					konamiIndex++;
				} else {
					konamiIndex = 0;
				}
				if(konamiIndex == konamiCode.length - 1) {
					diameter = MAX_DIAMETER;
					Game.loop().detach(shrink);
				}
			}
		});
		Game.loop().attach(shrink);
	}

	private Path2D createHeartPath(float x, float y, float width, float height) {
		float beX = x + width / 2;  // bottom endpoint X
		float beY = y + height;     // bottom endpoint Y

		float c1DX = width  * 0.968f;  // delta X of control point 1
		float c1DY = height * 0.672f;  // delta Y of control point 1
		float c2DX = width  * 0.281f;  // delta X of control point 2
		float c2DY = height * 1.295f;  // delta Y of control point 2
		float teDY = height * 0.850f;  // delta Y of top endpoint

		Path2D.Float heartPath = new Path2D.Float();
		heartPath.moveTo(beX, beY);       // bottom endpoint
		// left side of heart
		heartPath.curveTo(
				beX - c1DX, beY - c1DY,   // control point 1
				beX - c2DX, beY - c2DY,   // control point 2
				beX       , beY - teDY);  // top endpoint
		// right side of heart
		heartPath.curveTo(
				beX + c2DX, beY - c2DY,   // control point 2
				beX + c1DX, beY - c1DY,   // control point 1
				beX       , beY);         // bottom endpoint
		return heartPath;
	}
}
