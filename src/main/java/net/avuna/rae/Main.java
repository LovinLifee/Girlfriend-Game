package net.avuna.rae;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.configuration.DisplayMode;
import net.avuna.rae.levels.Level1;
import net.avuna.rae.levels.Level5;
import net.avuna.rae.levels.Level6;
import net.avuna.rae.levels.WinningScreen;

public class Main {

	public static void main(String[] args) {
		Game.init(args);
		Game.config().graphics().setDisplayMode(DisplayMode.WINDOWED);
		Game.window().setTitle("Game for my Rae Bae <3");

		Game.screens().add(new Level6());

		Game.start();
	}
}