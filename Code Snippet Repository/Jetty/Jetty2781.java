    protected String findRequestName(ServletRequest request)
    {
        if (request==null)
            return null;
        HttpServletRequest r = (HttpServletRequest)request;
        String n = (String)request.getAttribute(_attr);
        if (n==null)
        {
            n=String.format("%s@%x",r.getRequestURI(),request.hashCode());
            request.setAttribute(_attr,n);
        }
        return n;
    }
