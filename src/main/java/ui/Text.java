package ui;

import core.BreakTheWall;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Text extends GameObject {

	private float grayScale;
	private float textSize;
	private BreakTheWall world;
	private String text;

	public Text(String text, float x, float y, float textSize, float grayScale, BreakTheWall world) {
		this.setX(x);
		this.setY(y);
		this.setWidth(world.width / 2);
		this.setHeight((int) textSize);

		this.grayScale = grayScale;
		this.textSize = textSize;

		this.text = text;
		this.world = world;

		world.addGameObject(this);
	}

	@Override
	public void update() {

	}

	public void destroy() {
		world.deleteGameObject(this);
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(grayScale);
		g.textSize(textSize);
		g.text(this.text, this.getX(), this.getY());
	}
}