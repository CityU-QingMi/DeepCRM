    private String addPaths(String servletPath, String pathInfo)
    {
        if (servletPath.length()==0)
            return pathInfo;
       
        if (pathInfo==null)
            return servletPath;
        
        return servletPath+pathInfo;
    }
