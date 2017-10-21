    protected int getPriority(ServletRequest request)
    {
        HttpServletRequest baseRequest = (HttpServletRequest)request;
        if (baseRequest.getUserPrincipal() != null)
        {
            return 2;
        }
        else
        {
            HttpSession session = baseRequest.getSession(false);
            if (session != null && !session.isNew())
                return 1;
            else
                return 0;
        }
    }
