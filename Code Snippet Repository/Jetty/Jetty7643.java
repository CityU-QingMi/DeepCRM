    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        String from = request.getRemoteAddr();
        String to = request.getLocalAddr();
        String path=((HttpServletRequest)request).getServletPath();
        
        if (!_remote && !_allowed.contains(path) && !from.equals(to))
        {
            _context.getRequestDispatcher("/remote.html").forward(request,response);
            return;
        }

        Integer old_value=null;
        ServletRequest r = request;
        while (r instanceof ServletRequestWrapper)
            r=((ServletRequestWrapper)r).getRequest();

        try
        {
            old_value=(Integer)request.getAttribute("testFilter");

            Integer value=(old_value==null)?new Integer(1):new Integer(old_value.intValue()+1);

            request.setAttribute("testFilter", value);

            String qString = ((HttpServletRequest)request).getQueryString();
            if (qString != null && qString.indexOf("wrap")>=0)
            {
                request=new HttpServletRequestWrapper((HttpServletRequest)request);
            }
            _context.setAttribute("request"+r.hashCode(),value);

            chain.doFilter(request, response);
        }
        finally
        {
            request.setAttribute("testFilter", old_value);
            _context.setAttribute("request"+r.hashCode(),old_value);
        }
    }
