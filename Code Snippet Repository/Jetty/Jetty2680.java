    @Override
    public UserIdentity login(String username, Object password, ServletRequest request)
    {
        
        UserIdentity user = super.login(username,password,request);
        if (user!=null)
        {
            HttpSession session = ((HttpServletRequest)request).getSession(true);
            Authentication cached=new SessionAuthentication(getAuthMethod(),user,password);
            session.setAttribute(SessionAuthentication.__J_AUTHENTICATED, cached);
        }
        return user;
    }
