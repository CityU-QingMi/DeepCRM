        protected CachedChain(List<FilterHolder> filters, ServletHolder servletHolder)
        {
            if (filters.size()>0)
            {
                _filterHolder=filters.get(0);
                filters.remove(0);
                _next=new CachedChain(filters,servletHolder);
            }
            else
                _servletHolder=servletHolder;
        }
