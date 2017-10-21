    protected boolean isIncluded (String name)
    {    
        for (String include:_includes)
        {
            if (SelectorUtils.matchPath(include, name, _caseSensitive))
            {
                return true;
            }
        }
        return false;
    }
