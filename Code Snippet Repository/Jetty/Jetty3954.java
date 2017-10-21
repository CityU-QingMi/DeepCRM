    public boolean containsPathSpec (String pathSpec)
    {
        if (_pathSpecs == null || _pathSpecs.length == 0)
            return false;
        
        for (String p:_pathSpecs)
        {
            if (p.equals(pathSpec))
                return true;
        }
        return false;
    }
