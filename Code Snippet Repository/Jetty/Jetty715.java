    public void init(FilterConfig filterConfig) throws ServletException
    {
        filterConfig.getServletContext().log("WARNING: " + this.getClass().getName() + " is now DEPRECATED, use Servlet 3.0 AsyncContext instead.");
        boolean jetty_7_or_greater="org.eclipse.jetty.servlet".equals(filterConfig.getClass().getPackage().getName());
        _context = filterConfig.getServletContext();

        String param=filterConfig.getInitParameter("debug");
        _debug=param!=null&&Boolean.parseBoolean(param);
        if (_debug)
            __debug=true;

        param=filterConfig.getInitParameter("partial");
        param=filterConfig.getInitParameter("faux");
        if (param!=null)
            _faux=Boolean.parseBoolean(param);
        else
            _faux=!(jetty_7_or_greater || _context.getMajorVersion()>=3);

        _filtered=_faux;
        if (_debug)
            _context.log("ContinuationFilter "+
                    " jetty="+jetty_7_or_greater+
                    " faux="+_faux+
                    " filtered="+_filtered+
                    " servlet3="+ContinuationSupport.__servlet3);
        _initialized=true;
    }
