package gameItems;

import java.util.List;

import core.BreakTheWall;
import levelShapes.Block;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Hieronder staat de code voor de Ball(s)
 * De positie van de ball, de snelheid, hoek van inval, en hoe ze terugkaatsen.
 */

public class Ball extends GameObject implements ICollidableWithGameObjects {

	private BreakTheWall world;
	private static int amountOfBalls = 0;

	public Ball(float width, BreakTheWall world) {
		this.setWidth((int) width);
		this.setHeight((int) this.getWidth());
		this.setX(world.width / 2);
		this.setY(world.height - (this.height + 100));

		setDirectionSpeed(35, 4);

		this.world = world;

		world.addGameObject(this);

		Ball.amountOfBalls++;
	}

	public void setAmountOfBalls(int totalBalls) {
		Ball.amountOfBalls = totalBalls;
	}

	public int getAmountOfBalls() {
		return Ball.amountOfBalls;
	}
	
	
	/**
	 * Updates Ball at screen Borders
	 */

	@Override
	public void update() {
		if (this.getX() + width >= world.width || this.getX() <= 0) {
			this.setxSpeed(this.getxSpeed() * -1);
		}

		if (this.getY() <= 0) {
			this.setySpeed(this.getySpeed() * -1);
		}

		if (this.getY() + height >= world.height) {
			System.out.println("You lost a ball");
			world.deleteGameObject(this);
			Ball.amountOfBalls--;
		}
		if (Ball.amountOfBalls == 0) {
			world.setScreen("End");
			System.out.println("You lost the game");
		}
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(0, 0, 255);
		g.stroke(0, 0, 255);
		g.rect(x, y, width, height);
	}
	
	/**
	 * Kijkt naar welke kant van het object de bal heeft geraakt en kaarst weg
	 */

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject object : collidedGameObjects) {
			if (object instanceof Block) {

				if (this.getDirection() >= 0 && this.getDirection() <= 90) {
					if ((this.getX() + this.getWidth()) - object.getX() <= this.getSpeed()) {
						this.setX(this.getX() - this.getWidth());
						this.setxSpeed(this.getxSpeed() * -1);
					} else if (this.getY() - (object.getY() + object.getHeight()) < this.getSpeed()
							&& this.getY() - (object.getY() + object.getHeight()) > this.getSpeed() * -1) {
						this.setY(this.getY() + this.getHeight());
						this.setySpeed(this.getySpeed() * -1);
					}
				} else if (this.getDirection() >= 90 && this.getDirection() <= 180) {
					if ((this.getX() + this.getWidth()) - object.getX() <= this.getSpeed()) {
						this.setX(this.getX() - this.getWidth());
						this.setxSpeed(this.getxSpeed() * -1);
					} else if (object.getY() - (this.getY() + this.getHeight()) <= this.getSpeed()
							&& object.getY() - (this.getY() + this.getHeight()) >= this.getSpeed() * -1) {
						this.setY(this.getY() - this.getHeight());
						this.setySpeed(this.getySpeed() * -1);
					}
				} else if (this.getDirection() >= 180 && this.getDirection() <= 270) {
					if (this.getX() - (object.getX() + object.getWidth()) <= this.getSpeed()
							&& this.getX() - (object.getX() + object.getWidth()) > this.getSpeed() * -1) {
						this.setX(this.getX() + this.getWidth());
						this.setxSpeed(this.getxSpeed() * -1);
					} else if ((this.getY() + this.getHeight()) - object.getY() <= this.getSpeed()) {
						this.setY(this.getY() - this.getHeight());
						this.setySpeed(this.getySpeed() * -1);
					}
				} else if (this.getDirection() >= 270 && this.getDirection() <= 360) {
					if ((object.getX() + object.getWidth()) - this.getX() <= this.getSpeed()) {
						this.setX(this.getX() + this.getWidth());
						this.setxSpeed(this.getxSpeed() * -1);
					} else if (this.getY() - (object.getY() + object.getHeight()) <= this.getSpeed()
							&& this.getY() - (object.getY() + object.getHeight()) > this.getSpeed() * -1) {
						this.setY(this.getY() + this.getHeight());
						this.setySpeed(this.getySpeed() * -1);
					}
				}
				((Block) object).destroy();

			}
			if (object instanceof Shield) {

				if (object.getAngleFrom(this) < 90) {
					float scale = object.getAngleFrom(this) / 10;
					this.setDirection(scale * 5);
				} else if (object.getAngleFrom(this) > 270) {
					float scale = (360 - object.getAngleFrom(this)) / 10;
					this.setDirection(360 - (scale * 5));
				}
			}
		}
	}
}
