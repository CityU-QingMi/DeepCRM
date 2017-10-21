    public String getObjectNameBasis()
    {
        if (_managed != null && _managed instanceof ServletMapping)
        {
            ServletMapping mapping = (ServletMapping)_managed;
            String name = mapping.getServletName();
            if (name != null)
                return name;
        }
        
        return super.getObjectNameBasis();
    }
