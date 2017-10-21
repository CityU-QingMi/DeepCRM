    @Override
    public UserIdentity login(String username, Object password, ServletRequest request)
    { 
        UserIdentity user = _loginService.login(username, password, request);
        if (user != null)
        {
            renewSession((HttpServletRequest)request, null);
            HttpSession session = ((HttpServletRequest)request).getSession(true);
            if (session != null)
            {
                SessionAuthentication sessionAuth = new SessionAuthentication(getAuthMethod(), user, password);
                session.setAttribute(SessionAuthentication.__J_AUTHENTICATED, sessionAuth);
            }
        }
        return user;
    }
