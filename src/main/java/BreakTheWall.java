import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class BreakTheWall extends GameEngine {

	public static void main(String[] args) {
		PApplet.main(new String[] { "BreakTheWall" });
	}

	private String screen;
	private String oldScreen = "None";

	@Override
	public void setupGame() {
		this.createView();
		setScreen("Main Menu");
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getScreen() {
		return this.screen;
	}

	public void setOldScreen(String screen) {
		this.oldScreen = screen;
	}

	public String getOldScreen() {
		return this.oldScreen;
	}

	private float getPercentageOfDisplay() {
		return 80;
	}

	private int screenWidth() {
		float tempScreenWidth = (this.displayWidth * (getPercentageOfDisplay() / 100));
		return (int) (tempScreenWidth);
	}

	private int screenHeight() {
		float tempScreenHeight = (this.displayHeight * (getPercentageOfDisplay() / 100));
		return (int) (tempScreenHeight);
	}

	public void createView() {
		View view = new View(screenWidth(), screenHeight());
		view.setBackground(0, 0, 0);

		this.setView(view);
		this.size(screenWidth(), screenHeight());
	}

	@SuppressWarnings("unused")
	public void drawObjects() {
		Shield shield = new Shield(this);
		Ball ball = new Ball(20, this);
		Rocket rocket = new Rocket(this);
	}

	@SuppressWarnings("unused")
	public void printScreen() {

		switch (screen) {

		case "Main Menu":

			System.out.println("Main Menu");
			PageTitel BreakTheWall = new PageTitel("Break The Wall", (this.getHeight() / 10), this.getWidth() / 2,
					this);
			MenuBlock start = new MenuBlock("Start", (this.getHeight() / 8) * 2, this.getWidth() / 2, 100,
					"Game Screen", this);
			MenuBlock levels = new MenuBlock("Levels", (this.getHeight() / 8) * 4, this.getWidth() / 2, 100, "Levels",
					this);
			MenuBlock highscores = new MenuBlock("Tutorial", (this.getHeight() / 8) * 6, this.getWidth() / 2, 100,
					"Tutorial", this);

			break;

		case "Levels":
			PageTitel Levels = new PageTitel("Levels", (this.getHeight() / 10), this.getWidth() / 2, this);
			MenuBlock level1 = new MenuBlock("Level1", this.getWidth() / 6, (this.getHeight() / 8) * 2,
					this.getWidth() / 6, 100, "1", this);
			MenuBlock Level2 = new MenuBlock("Level2", this.getWidth() / 6, (this.getHeight() / 8) * 4,
					this.getWidth() / 6, 100, "2", this);
			MenuBlock Level3 = new MenuBlock("Level3", this.getWidth() / 6, (this.getHeight() / 8) * 6,
					this.getWidth() / 6, 100, "3", this);
			MenuBlock Level4 = new MenuBlock("Level4", level1.getX() + this.getWidth() / 2, (this.getHeight() / 8) * 2,
					this.getWidth() / 6, 100, "4", this);
			MenuBlock Level5 = new MenuBlock("Level5", level1.getX() + this.getWidth() / 2, (this.getHeight() / 8) * 4,
					this.getWidth() / 6, 100, "5", this);
			MenuBlock Level6 = new MenuBlock("Level6", level1.getX() + this.getWidth() / 2, (this.getHeight() / 8) * 6,
					this.getWidth() / 6, 100, "6", this);

			break;

		case "Tutorial":
			PageTitel tutorial = new PageTitel("Tutorial", (this.getHeight() / 10), this.getWidth() / 2, this);
			MenuBlock ctrl = new MenuBlock("CTRL to pause", (this.getHeight() / 8) * 2, this.getWidth(), 100,
					"Main Menu", this);
			MenuBlock updown = new MenuBlock("Up/Down to change shield speed", (this.getHeight() / 8) * 4,
					this.getWidth(), 100, "Main Menu", this);
			MenuBlock space = new MenuBlock("Space, fire rocket", (this.getHeight() / 8) * 6, this.getWidth(), 100,
					"Main Menu", this);
			break;

		case "Game Screen":
			Level level = new Level(0, 0, 0, this);
			level.startLevel(Level.level);
			this.drawObjects();

			break;
		}
	}

	@Override
	public void update() {
		if (this.getScreen() == "Game Screen" && this.getOldScreen() == "Levels") {
			this.deleteAllGameOBjects();
			this.printScreen();
			this.setOldScreen(this.getScreen());
		} else if (this.getScreen() != this.getOldScreen()) {
			this.deleteAllGameOBjects();
			this.printScreen();
			this.setOldScreen(this.getScreen());
		}
		if (this.getScreen() == "Game Screen") {
			if (Rocket.countRockets != Rocket.previousCount) {
				Text text = new Text("Amount of Rockets left: " + Rocket.countRockets, 0, 30, 30, 255, this);
				Rocket.previousCount = Rocket.countRockets;
			}
		}
	}

}
