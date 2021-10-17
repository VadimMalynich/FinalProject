package by.training.finalproject.controller.command.impl;

import by.training.finalproject.bean.User;
import by.training.finalproject.controller.command.Command;
import by.training.finalproject.service.MessageManager;
import by.training.finalproject.service.ServiceException;
import by.training.finalproject.service.ServiceProvider;
import by.training.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {
	private static final Logger userLogger = LogManager.getLogger(SignIn.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();

		User user;
		try {
			user = userService.authorization(login, password);
			if (user == null) {
				response.sendRedirect("Controller?command=go_to_sign_in_page&message=message.signIn.incorrect");
				return;
			}
			session.setAttribute("user", user);
			response.sendRedirect("Controller?command=go_to_home_page&message=message.signIn.complete");
		} catch (ServiceException e) {
			userLogger.info(e);
			response.sendRedirect("Controller?command=go_to_sign_in_page&message=message.signIn.incorrect");
		}
	}
}
