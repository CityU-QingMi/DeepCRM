    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
        throws IOException, ServletException
    {
        String path=((HttpServletRequest)request).getServletPath();
        if (welcome!=null && path.endsWith("/"))
            request.getRequestDispatcher(path+welcome).forward(request,response);
        else
            chain.doFilter(request, response);
    }
