    @Override
    public boolean matches(final String path)
    {
        int idx = path.indexOf('?');
        if (idx >= 0)
        {
            // match only non-query part
            return getMatcher(path.substring(0,idx)).matches();
        }
        else
        {
            // match entire path
            return getMatcher(path).matches();
        }
    }
