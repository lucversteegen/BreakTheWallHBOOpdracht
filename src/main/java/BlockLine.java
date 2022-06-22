import java.util.ArrayList;

public class BlockLine {

	float amountOfBlocks, lineNumber;
	int hitsToBreak;

	BreakTheWall world;

	BlockLine(float amountOfBlocks, float lineNumber, int hitsToBreak, BreakTheWall world) {
		this.amountOfBlocks = amountOfBlocks;
		this.lineNumber = lineNumber;

		this.hitsToBreak = hitsToBreak;

		this.world = world;
	}

	private ArrayList<Block> blocks = new ArrayList<Block>();

	public void fillBlockLine() {

		float margin = 100 / (amountOfBlocks + 1);

		float blockWidth = (world.width - 100) / amountOfBlocks;
		float blockHeight = world.height / 20;
		float yBlock = (lineNumber + 1) * blockHeight;
		float xBlock = blockWidth;

		for (int i = 0; i < amountOfBlocks; i++) {
			Block tempBlock = new Block((i * xBlock) + (i * margin) + margin, yBlock + (lineNumber * 10), blockWidth,
					blockHeight, hitsToBreak, world);
			world.addGameObject(tempBlock);
			blocks.add(tempBlock);
		}
	}

	public void draw() {
		for (Block block : this.getBlockList()) {
			block.draw(world.g);
		}
	}

	ArrayList<Block> getBlockList() {
		fillBlockLine();
		return blocks;
	}
}
