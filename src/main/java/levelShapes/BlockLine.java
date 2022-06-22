package levelShapes;

import java.util.ArrayList;

import core.BreakTheWall;

/**
* Hieronder staat de code om een lijn met blocks te tekenen.
*/

public class BlockLine implements BlockShape {

	private float amountOfBlocks, lineNumber;
	private int hitsToBreak;

	private BreakTheWall world;

	public BlockLine(float amountOfBlocks, float lineNumber, int hitsToBreak, BreakTheWall world) {
		this.amountOfBlocks = amountOfBlocks;
		this.lineNumber = lineNumber;
		this.hitsToBreak = hitsToBreak;
		this.world = world;
		this.fillBlockLine();
	}

	private ArrayList<Block> blocks = new ArrayList<Block>();

	private void fillBlockLine() {

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
	
	@Override
	public void draw() {
		for (Block block : this.getBlocks()) {
			block.draw(world.g);
		}
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}
}
