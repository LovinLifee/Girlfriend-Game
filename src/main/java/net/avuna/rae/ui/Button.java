package net.avuna.rae.ui;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import lombok.Setter;

import java.awt.*;

@Setter
public class Button extends GuiComponent {

	private Color outlineColor;
	private Color textColor;

	private final Color defaultOutLineColor;
	private final Color defaultTextColor;

	public Button(double x, double y, double width, double height, Color outlineColor, Color textColor) {
		super(x, y, width, height);
		this.defaultOutLineColor = this.outlineColor = outlineColor;
		this.defaultTextColor = this.textColor = textColor;
		super.onHovered(e -> {
			setTextColor(textColor.darker().darker());
			setOutlineColor(outlineColor.darker().darker());
			Game.window().getRenderComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		});
		super.onMouseLeave(e -> {
			setTextColor(defaultTextColor);
			Game.window().cursor().showDefaultCursor();
			setOutlineColor(defaultOutLineColor);
		});
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(outlineColor);
		g.drawRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
		g.setColor(textColor);
		if(getText() != null) {
			TextRenderer.render(g, getText(), getX()  + (TextRenderer.getWidth(g, getText())) / 2, getY() + getHeight() / 2);
		}
	}
}
