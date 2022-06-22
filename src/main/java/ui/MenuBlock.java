package ui;
import core.BreakTheWall;
import gameItems.Level;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class MenuBlock extends GameObject {

	public String text;
	private BreakTheWall world;
	private String link;

	public MenuBlock(String text, float y, float width, float height, String link, BreakTheWall world) {
		this.setWidth((int) width);
		this.setHeight((int) height);
		this.setX((world.getWidth() / 2) - (this.getWidth() / 2));
		this.setY(y);

		this.text = text;
		this.link = link;
		this.world = world;

		world.addGameObject(this);
	}

	public MenuBlock(String text, float x, float y, float width, float height, String link, BreakTheWall world) {
		this.setWidth((int) width);
		this.setHeight((int) height);
		this.setX(x);
		this.setY(y);

		this.text = text;
		this.link = link;
		this.world = world;

		world.addGameObject(this);
	}

	public void draw(PGraphics g) {
		g.fill(255);
		g.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.fill(0);
		g.textAlign(CENTER, CENTER);
		g.textSize(this.getHeight() - 20);
		g.text(this.text, this.getCenterX(), this.getCenterY());
	}

	boolean overRect() {
		if (world.mouseX >= this.getX() && world.mouseX <= this.getX() + this.getWidth() && world.mouseY >= this.getY()
				&& world.mouseY <= this.getY() + this.getHeight()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void update() {
		if (this.overRect() && world.mousePressed) {
			Level level = new Level(world);
			if (link == "1") {
				world.setScreen("Game Screen");
				level.startLevel(1);

			} else if (link == "2") {
				world.setScreen("Game Screen");
				level.startLevel(2);

			} else if (link == "3") {
				world.setScreen("Game Screen");
				level.startLevel(3);

			} else if (link == "4") {
				world.setScreen("Game Screen");
				level.startLevel(4);

			} else if (link == "5") {
				world.setScreen("Game Screen");
				level.startLevel(5);

			} else if (link == "6") {
				world.setScreen("Game Screen");
				level.startLevel(6);

			}else{
			world.setScreen(link);
			world.delay(500);
			}
		}
	}
}
