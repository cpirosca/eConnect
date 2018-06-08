package application;

public class Make {

	public void createBondBetweenAssignmentAndGroup(String[] groupName, String assignmentDescription) {
		int counter = 0;
		Connector.assignmentCreation("", assignmentDescription);
		while (groupName.length > counter) {
			Connector.assignGroup(Connector.getAssignmentId(assignmentDescription), groupName[counter]);
			counter++;
		}
	}
}
