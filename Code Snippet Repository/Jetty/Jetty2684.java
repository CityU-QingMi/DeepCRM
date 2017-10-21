    public UserIdentity login(String username, Object password, ServletRequest request)
    {
        UserIdentity user = _loginService.login(username,password, request);
        if (user!=null)
        {
            renewSession((HttpServletRequest)request, (request instanceof Request? ((Request)request).getResponse() : null));
            return user;
        }
        return null;
    }
