package levelShapes;

import java.util.ArrayList;

import core.BreakTheWall;

public class BlockRectangle implements BlockShape {

	private ArrayList<BlockLine> blockLines;
	private int height, blocksPerLine, hitsToBreak;
	private BreakTheWall world;

	public BlockRectangle(int height, int blocksPerLine, int hitsToBreak, BreakTheWall world) {
		this.height = height;
		this.blocksPerLine = blocksPerLine;
		this.hitsToBreak = hitsToBreak;
		this.world = world;
		this.blockLines = new ArrayList<BlockLine>();
		fillLevel();
	}

	private void fillLevel() {
		for (int i = 0; i < height; i++) {
			BlockLine temp = new BlockLine(blocksPerLine, i, hitsToBreak, world);
			this.getBlockLines().add(temp);
		}
	}
	
	private ArrayList<BlockLine> getBlockLines(){
		return blockLines;
	}
	
	@Override
	public void draw() {
		for (BlockLine blockLines : this.getBlockLines()) {
			blockLines.draw();
		}
	}

}
