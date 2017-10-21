    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        _context= filterConfig.getServletContext();
        _remote=Boolean.parseBoolean(filterConfig.getInitParameter("remote"));
        _allowed.add("/favicon.ico");
        _allowed.add("/jetty_banner.gif");
        _allowed.add("/remote.html");

        LOG.debug("TestFilter#remote="+_remote);
    }
