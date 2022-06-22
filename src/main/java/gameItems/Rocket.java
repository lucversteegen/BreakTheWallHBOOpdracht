package gameItems;
import java.util.List;

import core.BreakTheWall;
import levelShapes.Block;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 *Maak een Raket die een blokje altijd direct sloopt
 */

public class Rocket extends GameObject implements ICollidableWithGameObjects {

	private BreakTheWall world;

	public static int countRockets = 1;
	public static int previousCount = -1;
	public static int rocketsOnScreen = 0;

	public Rocket(BreakTheWall world) {
		this.setWidth(world.width / 25);
		this.setHeight(world.height / 10);

		this.setX((world.width / 2) - (this.getWidth() / 2));
		this.setY(world.height + this.getHeight());

		this.world = world;

		world.addGameObject(this);
	}

	private void setX() {
		for (GameObject gameObject : world.getGameObjectItems()) {
			if (gameObject instanceof Shield) {
				this.setX(gameObject.getCenterX());
			}
		}
	}

	public void makeRocket() {
		Rocket rocket = new Rocket(world);
		world.addGameObject(rocket);
	}

	@Override
	public void update() {
		if (this.getY() + this.getHeight() < 0) {
			this.destroy();
		}

	}
	
	/**
	 * Activeren bij indrukken spatiebalk
	 *
	 */

	public void keyPressed(int keyCode, char key) {
		if (keyCode == 32 && countRockets > 0 && rocketsOnScreen == 0) {
			this.makeRocket();
			this.setX();
			this.setVisible(true);
			this.setDirectionSpeed(0, 5);
			Rocket.rocketsOnScreen++;
			Rocket.countRockets--;
		}
	}

	public void destroy() {
		world.deleteGameObject(this);
		rocketsOnScreen--;
	}

	@Override
	public void draw(PGraphics g) {
		g.noStroke();
		g.fill(200);
		g.rect(x, y, width, height);
		g.fill(255, 0, 0);
		g.triangle(x - (this.getWidth() / 2), y + (this.getHeight() / 4), x + (this.getWidth() + (this.getWidth() / 2)),
				y + (this.getHeight() / 4), x + (this.getWidth() / 2), y - (this.getHeight() / 4));
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject object : collidedGameObjects) {
			if (object instanceof Block) {
				((Block) object).setHitsToBreak(1);
				((Block) object).destroy();
				this.destroy();
			}
		}
	}

}
