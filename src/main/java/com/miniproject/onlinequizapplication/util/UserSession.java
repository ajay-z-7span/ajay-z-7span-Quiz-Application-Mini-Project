package com.miniproject.onlinequizapplication.util;

import com.miniproject.onlinequizapplication.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UserSession {
    private static User user = null;

    public static void setUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            user = (User) session.getAttribute("User");
        }
    }

    public static User getUserSession() {
        return (user != null) ? user : null;
    }
}
