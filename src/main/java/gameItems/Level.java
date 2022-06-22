package gameItems;

import core.BreakTheWall;
import levelShapes.BlockRectangle;
import levelShapes.BlockShape;

/**
 * Hieronder staat de code voor de verschillende level's, hoeveel lines van blocks er zijn,
 * Hoeveel blocks er op een lines staan, en hoevaak de blocks geraakt moeten worden voordat ze weggaan.
 */

public class Level {

	public static int level = 1;

	private BreakTheWall world;

	public Level(BreakTheWall world) {
		super();
		this.world = world;
	}

	public void startLevel(int level) {
		Level.level = level;
		BlockShape levelShape = null;
		switch (level) {
		case 1: {
			levelShape = new BlockRectangle(2, 5, 1, world);
			break;
		}
		case 2: {
			levelShape = new BlockRectangle(5, 2, 1, world);
			break;
		}
		case 3: {
			levelShape = new BlockRectangle(3, 3, 1, world);
			break;
		}
		case 4: {
			levelShape = new BlockRectangle(2, 1, 5, world);
			break;
		}
		case 5: {
			levelShape = new BlockRectangle(5, 3, 4, world);
			break;
		}
		case 6: {
			levelShape = new BlockRectangle(4, 4, 20, world);
			break;
		}
		}
		levelShape.draw();
	}
}