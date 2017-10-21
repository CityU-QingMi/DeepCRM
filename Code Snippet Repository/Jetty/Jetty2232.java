        public void contextInitialized(ServletContextEvent sce)
        {
            Enumeration<String> e=_ctx.getInitParameterNames();
            while (e.hasMoreElements())
            {
                String name = e.nextElement();
                _ctx.setInitParameter(name,expandParameter(_ctx.getInitParameter(name)));
            }
            
            ServletHandler servletHandler = _ctx.getChildHandlerByClass(ServletHandler.class);
            if (servletHandler!=null)
            {
                List<Holder<?>> holders = new ArrayList<Holder<?>>();
                if (servletHandler.getFilters()!=null)
                    holders.addAll(Arrays.asList(servletHandler.getFilters()));
                if (servletHandler.getHandler()!=null)
                    holders.addAll(Arrays.asList(servletHandler.getServlets()));
                for (Holder<?> holder: holders)
                {
                    e=holder.getInitParameterNames();
                    while (e.hasMoreElements())
                    {
                        String name = e.nextElement();
                        holder.setInitParameter(name,expandParameter(holder.getInitParameter(name)));
                    }
                }
            }
        }
