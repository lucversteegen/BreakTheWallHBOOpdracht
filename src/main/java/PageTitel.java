import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class PageTitel extends GameObject {

	private String text;

	PageTitel(String text, float y, float width, BreakTheWall world) {
		this.text = text;
		this.setY(y);
		this.setWidth((int) width);
		this.setHeight((int) height);

		this.setX(world.getWidth() / 2);

		this.text = text;

		world.addGameObject(this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(PGraphics g) {
		g.fill(255);
		g.textSize(150);
		g.textAlign(CENTER, CENTER);
		g.text(this.text, this.getX(), this.getY());

	}

}
