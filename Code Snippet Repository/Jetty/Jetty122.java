    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
    throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)arg0;
        HttpServletResponse response = (HttpServletResponse)arg1;
        HttpSession session = request.getSession(true);
        String val = request.getParameter("action");
        if (val!=null)
            session.setAttribute("action", val);
        arg2.doFilter(request, response);
    }
