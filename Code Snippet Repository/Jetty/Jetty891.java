    private String resolve(HttpServletRequest request, String value)
    {
        String path = request.getServletPath();
        String info = request.getPathInfo();
        if (info != null)
            path += info;
        if (!path.startsWith("/"))
            path = "/" + path;
        return value.replaceAll("\\$path", path);
    }
