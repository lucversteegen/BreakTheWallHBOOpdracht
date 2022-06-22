import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Block extends GameObject {

	BreakTheWall world;
	int hitsToBreak;
	int dropChance = 50;
	int goodChance = 50;

	Block(float x, float y, float width, float height, int hitsToBreak, BreakTheWall world) {
		this.setX(x);
		this.setY(y);
		this.setWidth((int) width);
		this.setHeight((int) height);

		this.hitsToBreak = hitsToBreak;

		this.world = world;
	}

	public void draw(PGraphics g) {
		g.fill(0, 0, 0);
		setStroke(g);
		g.rect(x, y, width, height);
	}

	@Override
	public void update() {

	}

	private void setStroke(PGraphics g) {
		if (hitsToBreak == 1) {
			g.stroke(0, 255, 0);
		}
		if (hitsToBreak == 2) {
			g.stroke(255, 0, 255);
		}
		if (hitsToBreak == 3) {
			g.stroke(0, 255, 255);
		}
		if (hitsToBreak > 3) {
			g.stroke(255, 255, 255);
			g.fill(50);
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
		}
	}
}
