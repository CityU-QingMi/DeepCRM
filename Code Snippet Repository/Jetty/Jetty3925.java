    public MappedResource<ServletHolder> getMappedServlet(String target)
    {
        if (target.startsWith("/"))
        {
            if (_servletPathMap==null)
                return null;
            return _servletPathMap.getMatch(target);
        }
        
        if (_servletNameMap==null)
            return null;
        ServletHolder holder = _servletNameMap.get(target);
        if (holder==null)
            return null;
        return new MappedResource<>(null,holder);
    }
