    protected synchronized boolean containsFilterHolder (FilterHolder holder)
    {
        if (_filters == null)
            return false;
        boolean found = false;
        for (FilterHolder f:_filters)
        {
            if (f == holder)
                found = true;
        }
        return found;
    }
