import java.util.ArrayList;

public class Level {

	int lines;
	int blocksPerLine;
	int hitsToBreak;

	public static int level = 1;

	BreakTheWall world;

	Level(int lines, int blocksPerLine, int hitsToBreak, BreakTheWall world) {

		this.lines = lines;
		this.blocksPerLine = blocksPerLine;

		this.hitsToBreak = hitsToBreak;

		this.world = world;
	}

	private ArrayList<BlockLine> blockLines = new ArrayList<BlockLine>();

	public void fillLevel() {
		for (int i = 0; i < lines; i++) {
			BlockLine tempBlockLine = new BlockLine(blocksPerLine, i, hitsToBreak, world);
			blockLines.add(tempBlockLine);
		}
	}

	public void draw() {
		for (BlockLine blockLines : this.getLevel()) {
			blockLines.draw();
		}
	}

	ArrayList<BlockLine> getLevel() {
		fillLevel();
		return blockLines;
	}

	public void startLevel(int level) {
		Level.level = level;

		switch (level) {
		case 1:
			Level level1 = new Level(2, 6, 1, world);
			level1.draw();
			break;

		case 2:
			Level level2 = new Level(5, 10, 999, world);
			level2.draw();
			break;

		case 3:
			// Block block = new Block(width/2,height/2,200,200,1,getWorld());
			// addGameObject(block);
			break;
		}
	}
}