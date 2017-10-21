    private static String getCookieValue(Cookie[] cookies, String cookieName,
            String defaultValue) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return defaultValue;
    }
