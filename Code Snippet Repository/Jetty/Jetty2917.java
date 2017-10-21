    @Override
    public RequestDispatcher getRequestDispatcher(String path)
    {
        // path is encoded, potentially with query
        
        path = URIUtil.compactPath(path);

        if (path == null || _context == null)
            return null;

        // handle relative path
        if (!path.startsWith("/"))
        {
            String relTo = URIUtil.addPaths(_servletPath,_pathInfo);
            int slash = relTo.lastIndexOf("/");
            if (slash > 1)
                relTo = relTo.substring(0,slash + 1);
            else
                relTo = "/";
            path = URIUtil.addPaths(relTo,path);
        }

        return _context.getRequestDispatcher(path);
    }
