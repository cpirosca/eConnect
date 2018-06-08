package application;

import java.sql.*;

import com.sun.glass.ui.TouchInputSupport;

public class Connector {
	private static String connectionString = "jdbc:mysql://localhost:3306/econnect";
	private static Statement command;
	private static Connection connection;
	private static ResultSet data;
	private static String user = "root";
	private static String password = "";

	public static String getPassword(String username) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT hash FROM users WHERE username = " + "'" + username + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (data.first()) {
				return data.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static boolean checkSpecializare(String specializare) {
		if (specializare.compareTo("Calculatoare Romana") == 0 || specializare.compareTo("Calculatoare Engleza") == 0
				|| specializare.compareTo("Automatica Romana") == 0
				|| specializare.compareTo("Automatica Engleza") == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkGroup(String group) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT * FROM group_name WHERE group_name = " + "'" + group + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (data.first()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int accessLevel(String username) {
		int accessNumber = 0;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT Access_id FROM users WHERE username = " + "'" + username + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (data.first()) {
					accessNumber = data.getInt("Access_id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return accessNumber;
	}

	public static boolean updateStudent(String lastName, String firstName, String cnp, String groupID,
			String specialization) {
		boolean bool = true;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			command.execute("UPDATE students SET FirstName = " + "'" + firstName + "' , LastName =  " + "'" + lastName
					+ "', Group_id =  " + "'" + groupID + "', Specialization = " + "'" + specialization
					+ "' WHERE CNP = " + cnp + ";");
		} catch (SQLException e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	public static boolean insertStudent(String lastName, String firstName, String cnp, String groupID,
			String specialization) {
		boolean bool = true;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			command.execute("INSERT INTO students (FirstName, LastName, Group_name, CNP, Specialization) VALUES ('"
					+ firstName + "', '" + lastName + "' ,'" + groupID + "' ,'" + cnp + "', '" + specialization + "')");
		} catch (SQLException e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	public static boolean deleteStudent(String cnp) {
		boolean bool = true;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			command.execute("DELETE FROM students WHERE CNP = " + cnp);
		} catch (SQLException e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	public static boolean updateTeacher(String firstName, String lastName, String subject, String title,
			int teacherId) {
		boolean bool = true;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			command.execute("UPDATE teachers SET FirstName = " + "'" + firstName + "', LastName =  " + "'" + lastName
					+ "', Subject =  " + "'" + subject + "', Title = " + "'" + title + "' WHERE id = " + teacherId);
		} catch (SQLException e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	public static boolean insertTeacher(String firstName, String lastName, String subject, String title,
			int teacherId) {
		boolean bool = true;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			command.execute("INSERT INTO teachers (FirstName, LastName, Subject, Title, id) VALUES ('" + firstName
					+ "', '" + lastName + "', '" + subject + "', '" + title + "', '" + teacherId + "')");
		} catch (SQLException e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	public static boolean deleteTeacher(int teacherId) {
		boolean bool = true;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			command.execute("DELETE FROM teachers WHERE id = " + "'" + teacherId + "'");
		} catch (SQLException e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	public static boolean changeGrade(String cnp, int teacherId, float grade) {
		boolean bool = true;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			command.execute("UPDATE grades SET Grade = '" + grade + "' WHERE Student_id = '" + cnp
					+ "' AND Teacher_id = '" + teacherId + "'");
		} catch (SQLException e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	public static boolean assignmentCreation(String name, String description) {
		boolean bool = true;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			command.execute(
					"INSERT INTO assignments (Name, Description) VALUES ('" + name + "', '" + description + "')");
		} catch (SQLException e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	public static boolean assignGroup(int assignmentId, String group_name) {
		boolean bool = true;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			command.execute("INSERT INTO teams_4_projects (group_name, Assignment_id) VALUES ('" + group_name + "', '"
					+ assignmentId + "')");
		} catch (SQLException e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	public static ResultSet studentInfo(String firstName, String lastName, String cnp, String groupId,
			String specialization) { // cautare dinamica, daca un field ii gol sau mai multe
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			firstName = firstName + "%";
			lastName = lastName + "%";
			cnp = cnp + "%";
			groupId = groupId + "%";
			specialization ="%"+ specialization+ "%";
			data = command.executeQuery("Select * FROM students where FirstName LIKE '" + firstName
					+ "' AND LastName LIKE '" + lastName + "' AND CNP LIKE '" + cnp + "' AND Group_name LIKE '" + groupId
					+ "' AND Specialization LIKE '" + specialization + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				data.beforeFirst();
				return data;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String searchByCnp(String cnp) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT * FROM students WHERE CNP = '" + cnp + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				data.beforeFirst();
				if (data.first()) {
					return data.getString(3) + " " + data.getString(2) + " " + data.getString(1) + " "
							+ data.getString(4) + " " + data.getString(5) + " " + data.getString(6);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static boolean checkCNP(String cnp) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT * FROM students WHERE CNP = '" + cnp + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (data.first()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static ResultSet searchByUserStudentInfo(String username) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery(
					"SELECT LastName, FirstName, Group_name, Specialization,CNP FROM students WHERE User_id = (SELECT id FROM users WHERE username = '"
							+ username + "')");
			data.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	public static ResultSet assignmentById(String id) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery(
					"SELECT Name, Description FROM assignments WHERE id = '" + id + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static ResultSet assignmentByUser(String username) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery(
					"SELECT Assignment_id FROM teams_4_projects WHERE group_name = (SELECT group_name FROM students WHERE User_id = (SELECT id FROM users WHERE username = '"
							+ username + "'))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static ResultSet subjectByUser(String username) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT " + "    subjects.name " + "FROM " + "    subjects " + "WHERE "
					+ "    subjects.id IN (SELECT " + "            subject_id " + "        FROM "
					+ "            schedule" + "        WHERE " + "            CNP = (SELECT "
					+ "                    CNP " + "                FROM " + "                    students "
					+ "                WHERE " + "                    User_id = (SELECT "
					+ "                            id " + "                        FROM "
					+ "                            users " + "                        WHERE "
					+ "                            username = '" + username + "')))" + "ORDER BY subjects.id");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static ResultSet gradeByUser(String username) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT " + "    grade " + "FROM " + "    grades " + "WHERE "
					+ "    student_id = (SELECT " + "            CNP " + "        FROM " + "            students "
					+ "        WHERE " + "            User_id = (SELECT " + "                    id "
					+ "                FROM " + "                    users " + "                WHERE "
					+ "                    username = '" + username + "'))" + "ORDER BY subject_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static ResultSet studentsByTeacherUsername(String username) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT " + "    LastName, FirstName, CNP " + "FROM " + "    students " + "WHERE "
					+ "    Group_name IN (SELECT " + "            group_name " + "        FROM "
					+ "            group_name " + "        WHERE " + "            group_id IN (SELECT "
					+ "                    group_id " + "                FROM " + "                    teacher_group "
					+ "                WHERE " + "                    teacher_id = (SELECT "
					+ "                            teacher_id " + "                        FROM "
					+ "                            teachers " + "                        WHERE "
					+ "                            id = (SELECT " + "                                    id "
					+ "                                FROM " + "                                    users "
					+ "                                WHERE " + "                                    username = '"
					+ username + "'))))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static ResultSet groupsByTeacherUsername(String username) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT " + "    group_name " + "FROM " + "    group_name " + "WHERE "
					+ "    group_id IN (SELECT " + "            group_id " + "        FROM "
					+ "            teacher_group " + "        WHERE " + "            teacher_id = (SELECT "
					+ "                    teacher_id " + "                FROM " + "                    teachers "
					+ "                WHERE " + "                    id = (SELECT " + "                            id "
					+ "                        FROM " + "                            users "
					+ "                        WHERE " + "                            username = '" + username
					+ "')))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	public static int getAssignmentId(String assignmentDescription) {
		int id = 0;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT id " + 
					"FROM assignments " + 
					"WHERE description = '" + assignmentDescription + "'");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(data.first()) {
					id = data.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	public static void createBondBetweenAssignmentAndGroup(String[] groupName, String assignmentDescription, String username) {
		int counter = 0;
		Connector.assignmentCreation(Connector.getTeachersSubject(username), assignmentDescription);
		while (groupName.length > counter) {
			Connector.assignGroup(Connector.getAssignmentId(assignmentDescription), groupName[counter]);
			counter++;
		}

	}
	
	public static void awardGrade(String cnp, float grade, String username) {
		int subjectId = 0;
		subjectId = Connector.getTeachersSubjectId(username);
		Connector.awardGrade1(cnp, subjectId, grade);
	}
	public static void awardGrade1(String cnp, int subjectId, float grade) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			command.execute("INSERT INTO grades (Grade, Student_id, Subject_id) VALUES ('" + grade + "', '"
					+ cnp + "', '" + subjectId + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String getTeachersSubject(String username) {
		String subject = null;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT name FROM subjects WHERE id = (SELECT Subject_id FROM teachers WHERE id = (SELECT id FROM users WHERE username = '" + username + "'))");
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(data.first()) {
					subject = data.getString("name");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return subject;
	}
	public static int getTeachersSubjectId(String username) {
		int subjectId = 0;
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT Subject_id FROM teachers WHERE id = (SELECT id FROM users WHERE username = '" + username + "')");
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(data.first()) {
					subjectId = data.getInt("Subject_id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return subjectId;
	}
	public static ResultSet getGradesAndSubjectsByStudentUsername(String username) {
		try {
			connection = DriverManager.getConnection(connectionString, user, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT subjects.name, grades.Grade " + 
					"FROM grades JOIN subjects ON (grades.Subject_id = subjects.id) JOIN students ON (grades.Student_id = students.CNP) JOIN schedule ON (schedule.subject_id = subjects.id AND schedule.CNP = students.CNP) " + 
					"WHERE students.User_id = (SELECT id FROM users WHERE username = '" + username + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

}
