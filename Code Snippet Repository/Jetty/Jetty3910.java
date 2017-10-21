    public void addFilter (FilterHolder filter)
    {
        if (filter == null)
            return;

        synchronized (this)
        {
            if (!containsFilterHolder(filter))
                setFilters(ArrayUtil.addToArray(getFilters(), filter, FilterHolder.class));
        }
    }
