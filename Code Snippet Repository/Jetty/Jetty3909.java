    public void addFilter (FilterHolder filter, FilterMapping filterMapping)
    {
        if (filter != null)
        {
            synchronized (this)
            {
                if (!containsFilterHolder(filter))
                    setFilters(ArrayUtil.addToArray(getFilters(), filter, FilterHolder.class));
            }
        }
        if (filterMapping != null)
            addFilterMapping(filterMapping);
    }
