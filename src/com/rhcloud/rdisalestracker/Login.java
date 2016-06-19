package com.rhcloud.rdisalestracker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.rdisalestracker.user.User;
import com.rhcloud.rdisalestracker.user.UserController;

import rx.functions.Action1;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserController controller = new UserController();
		Action1<User> onNext = new Action1<User>() {
			@Override
			public void call(User u) {
				try {
					response.getWriter().append("Welcome! ").append(u.getUsername());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		try {
			controller.login(request.getParameter("username"), request.getParameter("password"))
				.subscribe(onNext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
