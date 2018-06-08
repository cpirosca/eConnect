package controller;

import javafx.stage.Stage;

public class MainController {

	private static String user = null;
	private static Stage Search;
	
	public static Stage getSearch() {
		return Search;
	}

	public static void setSearch(Stage search) {
		Search = search;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user1) {
		user = user1;
	}
}
