package application;

public class Assignments {

	private String title, description;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Assignments(String title, String description) {
		this.title = title;
		this.description = description;
	}
}
