    public void addFilterWithMapping (FilterHolder holder,String pathSpec,int dispatches)
    {
        FilterHolder[] holders = getFilters();
        if (holders!=null)
            holders = holders.clone();

        try
        {
            synchronized (this)
            {
                if (holder != null && !containsFilterHolder(holder))
                    setFilters(ArrayUtil.addToArray(holders, holder, FilterHolder.class));
            }

            FilterMapping mapping = new FilterMapping();
            mapping.setFilterName(holder.getName());
            mapping.setPathSpec(pathSpec);
            mapping.setDispatches(dispatches);
            addFilterMapping(mapping);
        }
        catch (Throwable e)
        {
            setFilters(holders);
            throw e;
        }

    }
