    public synchronized void setServlets(ServletHolder[] holders)
    {
        if (holders!=null)
            for (ServletHolder holder:holders)
                holder.setServletHandler(this);
        
        updateBeans(_servlets,holders);
        _servlets=holders;
        updateNameMappings();
        invalidateChainsCache();
    }
