    protected synchronized boolean containsServletHolder (ServletHolder holder)
    {
        if (_servlets == null)
            return false;
        boolean found = false;
        for (ServletHolder s:_servlets)
        {
            if (s == holder)
                found = true;
        }
        return found;
    }
