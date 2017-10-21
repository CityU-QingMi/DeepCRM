    protected boolean isExcluded (String name)
    {
        for (String exclude:_excludes)
        {
            if (SelectorUtils.matchPath (exclude, name, _caseSensitive))
            {
                return true;
            }
        }
        return false;
    }
