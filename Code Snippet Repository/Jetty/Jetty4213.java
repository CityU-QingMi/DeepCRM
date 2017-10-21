    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        if (!_ok)
        {
            res.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return;
        }

        if (LOG.isDebugEnabled())
        {
            LOG.debug("CGI: ContextPath : " + req.getContextPath());
            LOG.debug("CGI: ServletPath : " + req.getServletPath());
            LOG.debug("CGI: PathInfo    : " + req.getPathInfo());
            LOG.debug("CGI: _docRoot    : " + _docRoot);
            LOG.debug("CGI: _path       : " + _path);
            LOG.debug("CGI: _ignoreExitState: " + _ignoreExitState);
        }

        // pathInContext may actually comprises scriptName/pathInfo...We will
        // walk backwards up it until we find the script - the rest must
        // be the pathInfo;
        String pathInContext = (_relative ? "" : StringUtil.nonNull(req.getServletPath())) + StringUtil.nonNull(req.getPathInfo());
        File execCmd = new File(_docRoot, pathInContext);
        String pathInfo = pathInContext;

        if (!_useFullPath)
        {
            String path = pathInContext;
            String info = "";

            // Search docroot for a matching execCmd
            while ((path.endsWith("/") || !execCmd.exists()) && path.length() >= 0)
            {
                int index = path.lastIndexOf('/');
                path = path.substring(0, index);
                info = pathInContext.substring(index, pathInContext.length());
                execCmd = new File(_docRoot, path);
            }

            if (path.length() == 0 || !execCmd.exists() || execCmd.isDirectory() || !execCmd.getCanonicalPath().equals(execCmd.getAbsolutePath()))
            {
                res.sendError(404);
            }

            pathInfo = info;
        }
        exec(execCmd, pathInfo, req, res);
    }
