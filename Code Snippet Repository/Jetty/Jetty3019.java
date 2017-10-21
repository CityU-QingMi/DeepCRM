    public boolean checkContextPath(String uri)
    {
        // Are we not the root context?
        if (_contextPath.length() > 1)
        {
            // reject requests that are not for us
            if (!uri.startsWith(_contextPath))
                return false;
            if (uri.length() > _contextPath.length() && uri.charAt(_contextPath.length()) != '/')
                return false;
        }
        return true;
    }
