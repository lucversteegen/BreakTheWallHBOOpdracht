import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Power extends GameObject implements ICollidableWithGameObjects {

	private BreakTheWall world;
	private float goodChance;
	private float dropChance;

	private boolean good;
	private boolean drop;

	Power(float x, float y, float width, float height, BreakTheWall world, int dropChance, int goodChance) {

		this.setX(x);
		this.setY(y);
		this.setWidth((int) width);
		this.setHeight((int) height);

		this.world = world;

		this.dropChance = dropChance;
		this.goodChance = goodChance;

		this.setDirectionSpeed(180, 5);

		this.setDrop();
		this.setGood();
	}

	private void pickPowerUp(GameObject object) {

		float powerRandom = world.random(0, 3);

		if (this.good) {
			if (powerRandom <= 1) {
				object.setWidth((int) (object.getWidth() + (object.getWidth() / 3)));
			} else if (powerRandom > 1 && 2 >= powerRandom) {
				Ball ball = new Ball(20, world);
				world.addGameObject(ball);
				ball.setX(object.getCenterX());
				ball.draw(world.g);
			} else if (powerRandom > 2) {
				for (GameObject gameObject : world.getGameObjectItems()) {
					if (gameObject instanceof Ball) {
						gameObject.setWidth((int) (gameObject.getWidth() + (gameObject.getWidth() / 2)));
						gameObject.setHeight((int) (gameObject.getHeight() + (gameObject.getHeight() / 2)));
					}
				}
			}
		}

		if (!this.good) {
			if (powerRandom <= 1) {
				object.setWidth((int) (object.getWidth() / 2));
			} else if (powerRandom > 1 && 2 >= powerRandom) {
				for (GameObject gameObject : world.getGameObjectItems()) {
					if (gameObject instanceof Ball) {
						gameObject.setSpeed((int) (gameObject.getSpeed() * 2));
					}
				}
			} else if (powerRandom > 2) {
				for (GameObject gameObject : world.getGameObjectItems()) {
					if (gameObject instanceof Ball) {
						gameObject.setWidth((int) (gameObject.getWidth() / 2));
						gameObject.setHeight((int) (gameObject.getHeight() / 2));
					}
				}
			}
		}

	}

	public void setGood() {

		float goodRandom = world.random(0, 100);

		if (this.goodChance >= goodRandom) {
			this.good = true;
		} else {
			this.good = false;
		}
	}

	private void setDrop() {

		float tempRandom = world.random(0, 100);

		if (dropChance >= tempRandom) {
			this.drop = true;
			world.addGameObject(this);
		} else {
			this.drop = false;
		}
	}

	@Override
	public void update() {
		if (this.getY() > world.height) {
			this.destory();
		}
	}

	@Override
	public void draw(PGraphics g) {

		if (this.good && this.drop) {
			g.noStroke();
			g.fill(0, 255, 0);
			g.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}
		if (this.good == false && this.drop) {
			g.noStroke();
			g.fill(255, 0, 0);
			g.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}

	}

	private void destory() {
		world.deleteGameObject(this);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject object : collidedGameObjects) {
			if (object instanceof Shield) {
				this.pickPowerUp(object);
				this.destory();
			}
		}

	}

}
