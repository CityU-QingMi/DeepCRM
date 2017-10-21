    public static String getContextRelativePath(ServletRequest request,
                                                String relativePath) {

        if (relativePath.startsWith("/"))
            return (relativePath);
        if (!(request instanceof HttpServletRequest))
            return (relativePath);
        HttpServletRequest hrequest = (HttpServletRequest) request;
        String uri = (String)
                request.getAttribute("javax.servlet.include.servlet_path");
        if (uri != null) {
            String pathInfo = (String)
                    request.getAttribute("javax.servlet.include.path_info");
            if (pathInfo == null) {
                if (uri.lastIndexOf('/') >= 0)
                    uri = uri.substring(0, uri.lastIndexOf('/'));
            }
        } else {
            uri = hrequest.getServletPath();
            if (uri.lastIndexOf('/') >= 0)
                uri = uri.substring(0, uri.lastIndexOf('/'));
        }
        return uri + '/' + relativePath;

    }
