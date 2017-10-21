    public synchronized void setFilters(FilterHolder[] holders)
    {
        if (holders!=null)
            for (FilterHolder holder:holders)
                holder.setServletHandler(this);
        
        updateBeans(_filters,holders);
        _filters=holders;
        updateNameMappings();
        invalidateChainsCache();
    }
