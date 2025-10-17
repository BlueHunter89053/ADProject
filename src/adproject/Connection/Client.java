/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adproject.Connection;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Albert
 */
public class Client {

    enum Action implements Serializable {
        LOGIN, ADD_STUDENT, ADD_COURSE, GET_COURSES
    }

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

    class Student implements Serializable {

        private final String studentNumber;
        private final String firstName;
        private final String lastName;

        public Student(String num, String fn, String ln, String em) {
            this.studentNumber = num;
            this.firstName = fn;
            this.lastName = ln;
        }

        @Override
        public String toString() {
            return studentNumber + " - " + firstName + " " + lastName;
        }
    }

    class Course implements Serializable {

        private final String code;
        private final String name;

        public Course(String code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public String toString() {
            return code + ": " + name;
        }
    }

    class User implements Serializable {

        private final String role;

        public User(String u, String p, String r) {
            role = r;
        }

        public String getRole() {
            return role;
        }
    }

// handle the client side socket connection
    class NetworkClient {

        private final Socket socket;
        private final ObjectOutputStream out;
        private final ObjectInputStream in;

        public NetworkClient(String host, int port) throws IOException {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }

        public Response send(Request req) throws IOException, ClassNotFoundException {
            out.writeObject(req);
            out.flush();
            return (Response) in.readObject();
        }

        public void close() throws IOException {
            socket.close();
        }
    }

// initialize the GUI Client
    private final JFrame frame;
    private final JTextField tfEmail;
    private final JTextField tfPassword;

    public Client() {
        frame = new JFrame("Student Enrolment Login");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 10, 10));
        frame.setLocationRelativeTo(null);

        JLabel lblUser = new JLabel("Username:");
        tfEmail = new JTextField(15);
        JLabel lblPass = new JLabel("Password:");
        tfPassword = new JPasswordField(15);
        JButton btnLogin = new JButton("Login");
        JButton btnExit = new JButton("Exit");

        frame.add(lblUser);
        frame.add(tfEmail);
        frame.add(lblPass);
        frame.add(tfPassword);
        frame.add(btnLogin);
        frame.add(btnExit);

        btnLogin.addActionListener(e -> login());
        btnExit.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private void login() {
        //use try and catch block to handle potential exceptions
        try {
            NetworkClient client = new NetworkClient("localhost", 9000);
            String[] creds = {tfEmail.getText(), tfPassword.getText()};
            Response res = client.send(new Request(Action.LOGIN, creds));

            if (res.isSuccess()) {
                User user = (User) res.getData();
                JOptionPane.showMessageDialog(frame, res.getMessage());
                if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                    adminScreen(client);
                } else {
                    studentScreen(client);
                }
            } else {
                JOptionPane.showMessageDialog(frame, res.getMessage());
            }
        } catch (HeadlessException | IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    private void adminScreen(NetworkClient client) {
        JFrame admin = new JFrame("Admin Dashboard");
        admin.setSize(500, 400);
        admin.setLayout(new GridLayout(4, 2, 10, 10));
//create text fields for user input
        JTextField tfCourseCode = new JTextField();
        JTextField tfCourseName = new JTextField();
        JTextField tfStudNum = new JTextField();
        JTextField tfFirst = new JTextField();
        JTextField tfLast = new JTextField();
        JTextField tfEmail;
        tfEmail = new JTextField();
//create buttons for user actions
        JButton btnAddCourse = new JButton("Add Course");
        JButton btnAddStudent = new JButton("Add Student");
        JButton btnView = new JButton("View Courses");

        admin.add(new JLabel("Course Code:"));
        admin.add(tfCourseCode);
        admin.add(new JLabel("Course Name:"));
        admin.add(tfCourseName);
        admin.add(new JLabel("Student Num:"));
        admin.add(tfStudNum);
        admin.add(new JLabel("First Name:"));
        admin.add(tfFirst);
        admin.add(new JLabel("Last Name:"));
        admin.add(tfLast);
        admin.add(new JLabel("Email:"));
        admin.add(tfEmail);
        admin.add(btnAddCourse);
        admin.add(btnAddStudent);
        admin.add(btnView);

        btnAddCourse.addActionListener((ActionEvent e) -> {
            try {
                Response res = client.send(new Request(Action.ADD_COURSE,
                        new Course(tfCourseCode.getText(), tfCourseName.getText())));
                JOptionPane.showMessageDialog(admin, res.getMessage());
            } catch (HeadlessException | IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(admin, ex.getMessage());
            }
        });

        btnAddStudent.addActionListener((ActionEvent e) -> {
            try {
                Student s = new Student(tfStudNum.getText(), tfFirst.getText(), tfLast.getText(), tfEmail.getText());
                Response res = client.send(new Request(Action.ADD_STUDENT, s));
                JOptionPane.showMessageDialog(admin, res.getMessage());
            } catch (HeadlessException | IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(admin, ex.getMessage());
            }
        });

        btnView.addActionListener((ActionEvent e) -> {
            try {
                Response res = client.send(new Request(Action.GET_COURSES, null));
                JOptionPane.showMessageDialog(admin, res.getData());
            } catch (HeadlessException | IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(admin, ex.getMessage());
            }
        });

        admin.setVisible(true);
    }

    private void studentScreen(NetworkClient client) {
        JFrame stud = new JFrame("Student Dashboard");
        stud.setSize(400, 300);
        stud.setLayout(new FlowLayout());
        JButton btnViewCourses = new JButton("View Courses");
        JButton btnExit = new JButton("Logout");
        stud.add(btnViewCourses);
        stud.add(btnExit);

        btnViewCourses.addActionListener((ActionEvent e) -> {
            try {
                Response res = client.send(new Request(Action.GET_COURSES, null));
                JOptionPane.showMessageDialog(stud, res.getData());
            } catch (HeadlessException | IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(stud, ex.getMessage());
            }
        });
        btnExit.addActionListener(e -> stud.dispose());

        stud.setVisible(true);
    }

    public static void main(String[] args) {
    }
}
