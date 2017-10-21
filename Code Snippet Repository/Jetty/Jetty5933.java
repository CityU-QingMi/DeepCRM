    public void start(WebAppContext context, Descriptor descriptor)
    {
        for (FilterHolder h : context.getServletHandler().getFilters())
        {
            _filterHolderMap.put(h.getName(),h);
            _filterHolders.add(h);
        }
        if (context.getServletHandler().getFilterMappings()!=null)
            _filterMappings.addAll(Arrays.asList(context.getServletHandler().getFilterMappings()));
        for (ServletHolder h : context.getServletHandler().getServlets())
        {
            _servletHolderMap.put(h.getName(),h);
            _servletHolders.add(h);
        }
        if (context.getServletHandler().getServletMappings()!=null)
            _servletMappings.addAll(Arrays.asList(context.getServletHandler().getServletMappings()));
    }
