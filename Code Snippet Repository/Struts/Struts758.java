    protected void addCookiesToResponse(CookieProvider action, HttpServletResponse response) {
        Set<Cookie> cookies = action.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Sending cookie [{}] with value [{}] for domain [{}]",
                            cookie.getName(), cookie.getValue(), (cookie.getDomain() != null ? cookie.getDomain() : "no domain"));
                }
                response.addCookie(cookie);
            }
        }
    }
