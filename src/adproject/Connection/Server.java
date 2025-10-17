/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adproject.Connection;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Albert
 */
public class Server {

    private static final int PORT = 9000;

    public static void main(String[] args) {
        System.out.println(" Server starting on port " + PORT + "...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println(" Server ready. Waiting for a client to connect...");

            Socket socket = serverSocket.accept();
            System.out.println(" Client connected.");

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                out.writeObject(response);
                out.flush();
            }

        } catch (Exception e) {
        }
    }

    // Handles each type of request from client
    private static Response handleRequest(Request req) {
        try {
            return switch (req.getAction()) {
                case LOGIN ->
                    handleLogin((String[]) req.getData());
                case ADD_COURSE ->
                    handleAddCourse((Course) req.getData());
                case ADD_STUDENT ->
                    handleAddStudent((Student) req.getData());
                case GET_COURSES ->
                    handleGetCourses();
                default ->
                    new Response(false, "Unknown action", null);
            };
        } catch (SQLException e) {
            return new Response(false, "Error: " + e.getMessage(), null);
        }
    }

    private static Response handleLogin(String[] creds) {
        String user = creds[0];
        String pass = creds[1];

        if (user.equals("admin") && pass.equals("admin")) {
            return new Response(true, "Welcome Admin!", new User("admin", "admin", "ADMIN"));
        } else if (user.equals("alice") && pass.equals("alice")) {
            return new Response(true, "Welcome Student!", new User("alice", "alice", "STUDENT"));
        }
        return new Response(false, "Invalid username or password", null);
    }

    private static Response handleAddCourse(Course c) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO COURSE (CODE, NAME) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getCode());
            ps.setString(2, c.getName());
            ps.executeUpdate();
        }
        return new Response(true, "Course added successfully!", null);
    }

    private static Response handleAddStudent(Student s) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO STUDENT (STUDENTNUMBER, FIRSTNAME, LASTNAME, EMAIL) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getStudentNumber());
            ps.setString(2, s.getFirstName());
            ps.setString(3, s.getLastName());
            ps.setString(4, s.getEmail());
            ps.executeUpdate();
        }
        return new Response(true, "Student added successfully!", null);
    }

    private static Response handleGetCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM COURSE";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                courses.add(new Course(rs.getString("CODE"), rs.getString("NAME")));
            }
        }
        return new Response(true, "Courses retrieved", courses);
    }
}

// ======== Shared Models ========
// Actions that the client can request from the server
enum Action implements Serializable {
    LOGIN, ADD_STUDENT, ADD_COURSE, GET_COURSES
}

// Generic request class sent from client to server
class Request implements Serializable {

    private final Action action;
    private final Object data;

    public Request(Action action, Object data) {
        this.action = action;
        this.data = data;
    }

    public Action getAction() {
        return action;
    }

    public Object getData() {
        return data;
    }
}

// Generic response class sent from server to client
class Response implements Serializable {

    private final boolean success;
    private final String message;
    private final Object data;

    public Response(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}

// Simple student class
class Student implements Serializable {

    private final String studentNumber;
    private final String firstName;
    private final String lastName;
    private final String email;

    public Student(String studentNumber, String firstName, String lastName, String email) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return studentNumber + " - " + firstName + " " + lastName;
    }
}

// Simple course class
class Course implements Serializable {

    private final String code;
    private final String name;

    public Course(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return code + ": " + name;
    }
}

// Simple user class
class User implements Serializable {

    private final String username;
    private final String password;
    private final String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

// ======== Database Connection ========
class DBConnection {

    private static final String URL = "jdbc:derby:StudentEnrolmentDB;create=true";

    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
