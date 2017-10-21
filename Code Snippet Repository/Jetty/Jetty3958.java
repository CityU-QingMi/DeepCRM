    public String getObjectNameBasis()
    {
        if (_managed != null && _managed instanceof FilterMapping)
        {
            FilterMapping mapping = (FilterMapping)_managed;
            String name = mapping.getFilterName();
            if (name != null)
                return name;
        }
        
        return super.getObjectNameBasis();
    }
