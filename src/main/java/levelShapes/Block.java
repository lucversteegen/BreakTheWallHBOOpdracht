package levelShapes;

import core.BreakTheWall;
import gameItems.Power;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Hieronder staat code voor de Blocks (rechthoek object)
 * Ook zit in deze code de kans voor een power up en power down.
 */

public class Block extends GameObject {

	private BreakTheWall world;
	private int hitsToBreak;
	private int dropChance = 50;
	private int goodChance = 50;
	public static int blocksMade = 0;
	public static int blocksDestroyed = 0;

	public Block(float x, float y, float width, float height, int hitsToBreak, BreakTheWall world) {
		this.setX(x);
		this.setY(y);
		this.setWidth((int) width);
		this.setHeight((int) height);
		this.hitsToBreak = hitsToBreak;
		this.world = world;
		Block.blocksMade++;
	}

	public void draw(PGraphics g) {
		g.fill(0, 0, 0);
		setStroke(g);
		g.rect(x, y, width, height);
	}

	@Override
	public void update() {

	}
	
	/**
	 * Zet Stroke gebaseerd op aantal hits tot ze kapot gaan
	 */

	private void setStroke(PGraphics g) {
		switch (hitsToBreak) {
		case 1: {
			g.fill(0, 255, 0);
			break;
		}
		case 2: {
			g.fill(255, 0, 255);
			break;
		}
		case 3: {
			g.fill(0, 255, 255);
			break;
		}
		default: {
			g.fill(100, 100, 100);
			g.stroke(50);
			break;
		}
		}
	}

	public void setDropChance(int dropChance) {
		this.dropChance = dropChance;
	}

	public void setGoodChance(int goodChance) {
		this.goodChance = goodChance;
	}

	public void setHitsToBreak(int hits) {
		this.hitsToBreak = hits;
	}
	
	/**
	 * Maakt een powerObject aan
	 */

	public void dropPower() {
		Power power = new Power(this.getX() + (this.getWidth() / 2), this.getY(), 20, 20, world, this.dropChance,
				this.goodChance);
		power.draw(world.g);
	}

	public void destroy() {
		if (this.hitsToBreak > 0) {
			this.hitsToBreak -= 1;
		}
		if (this.hitsToBreak == 0) {
			dropPower();
			world.deleteGameObject(this);
			Block.blocksDestroyed++;
		}
	}
}
