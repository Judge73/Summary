package ua.nure.kotkov.SummaryTask4.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kotkov.SummaryTask4.exception.AppException;

public abstract class Command implements Serializable{
	
	private static final long serialVersionUID = 4614827277069957538L;

	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, AppException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}
