package gameItems;
import core.BreakTheWall;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Shield extends GameObject {

	private BreakTheWall world;
	private int shieldSpeed = 15;
	private int keyCounter = 0;

	public Shield(BreakTheWall breakTheWall) {
		this.setWidth(breakTheWall.width / 8);
		this.setHeight(breakTheWall.height / 20);
		this.setX((breakTheWall.width / 2) - (width / 2));
		this.setY(breakTheWall.height - (height + 10));

		this.world = breakTheWall;

		breakTheWall.addGameObject(this);
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(PGraphics g) {
		g.fill(255, 0, 0);
		g.stroke(255, 0, 0);
		g.rect(x, y, width, height);
	}

	public void keyPressed(int keyCode, char key) {
		if (keyCode == LEFT && this.getX() > this.shieldSpeed && world.getThreadState() == false) {
			this.setX(this.getX() - this.shieldSpeed);
		}
		if (keyCode == RIGHT && this.getX() + this.getWidth() + this.shieldSpeed < world.width
				&& world.getThreadState() == false) {
			this.setX(this.getX() + this.shieldSpeed);
		}
		if (keyCode == UP) {
			if (keyCounter < 5) {
				this.shieldSpeed += 2;
				keyCounter++;
			}
		}
		if (keyCode == DOWN) {
			if (keyCounter > -5) {
				this.shieldSpeed -= 2;
				keyCounter--;
			}
		} else if (keyCode == CONTROL) {
			if (world.getThreadState()) {
				world.resumeGame();
			} else {
				world.pauseGame();
			}
		}
	}
}
