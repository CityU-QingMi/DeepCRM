    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
    {
        HttpServletRequest request = null;
        if (req instanceof HttpServletRequest)
            request = (HttpServletRequest)req;
        else
            throw new ServletException("Request not HttpServletRequest");

        String servletPath=null;
        String pathInfo=null;
        if (request.getAttribute(Dispatcher.INCLUDE_REQUEST_URI)!=null)
        {
            servletPath=(String)request.getAttribute(Dispatcher.INCLUDE_SERVLET_PATH);
            pathInfo=(String)request.getAttribute(Dispatcher.INCLUDE_PATH_INFO);
            if (servletPath==null)
            {
                servletPath=request.getServletPath();
                pathInfo=request.getPathInfo();
            }
        }
        else
        {
            servletPath = request.getServletPath();
            pathInfo = request.getPathInfo();
        }

        String pathInContext=URIUtil.addPaths(servletPath,pathInfo);

        if (pathInContext.endsWith("/"))
        {
            _dftServlet.getServlet().service(req,res);
        }
        else if (_starJspMapped && pathInContext.toLowerCase(Locale.ENGLISH).endsWith(".jsp"))
        {
            _jspServlet.getServlet().service(req,res);
        }
        else
        {

            Resource resource = _contextHandler.getResource(pathInContext);
            if (resource!=null && resource.isDirectory())
                _dftServlet.getServlet().service(req,res);
            else
                _jspServlet.getServlet().service(req,res);
        }

    }
