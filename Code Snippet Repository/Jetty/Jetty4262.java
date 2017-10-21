    protected boolean shouldFilter(HttpServletRequest http_request, HttpServletResponse http_response)
    {
        String http_method = http_request.getMethod();
        LOG.debug("HTTP method is: {}",http_method);
        if (!_httpMethods.test(http_method))
        {
            LOG.debug("should not apply filter because HTTP method does not match");
            return false;
        }

        String mime_type = guessMimeType(http_request,http_response);

        if (!_mimeTypes.test(mime_type))
        {
            LOG.debug("should not apply filter because mime type does not match");
            return false;
        }

        ServletContext context = http_request.getServletContext();
        String path = context == null?http_request.getRequestURI():URIUtil.addPaths(http_request.getServletPath(),http_request.getPathInfo());
        LOG.debug("Path is: {}",path);
        if (!_paths.test(path))
        {
            LOG.debug("should not apply filter because path does not match");
            return false;
        }

        return true;
    }
