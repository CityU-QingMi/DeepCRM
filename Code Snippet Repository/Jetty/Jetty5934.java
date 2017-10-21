    public void end(WebAppContext context, Descriptor descriptor)
    {
        context.getServletHandler().setFilters(_filterHolders.toArray(new FilterHolder[_filterHolderMap.size()]));
        context.getServletHandler().setServlets(_servletHolders.toArray(new ServletHolder[_servletHolderMap.size()]));

        context.getServletHandler().setFilterMappings(_filterMappings.toArray(new FilterMapping[_filterMappings.size()]));
        context.getServletHandler().setServletMappings(_servletMappings.toArray(new ServletMapping[_servletMappings.size()]));

        _filterHolderMap.clear();
        _filterHolders.clear();
        _filterMappings.clear();
        _servletHolderMap.clear();
        _servletHolders.clear();
        _servletMappings.clear();
    }
