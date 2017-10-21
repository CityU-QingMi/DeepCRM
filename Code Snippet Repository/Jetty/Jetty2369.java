    private String getBalancerMemberNameFromSessionCookie(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            for (Cookie cookie : cookies)
            {
                if (JSESSIONID.equalsIgnoreCase(cookie.getName()))
                    return extractBalancerMemberNameFromSessionId(cookie.getValue());
            }
        }
        return null;
    }
