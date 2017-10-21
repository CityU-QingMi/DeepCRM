    public void addServletWithMapping (ServletHolder servlet,String pathSpec)
    {
        ServletHolder[] holders=getServlets();
        if (holders!=null)
            holders = holders.clone();

        try
        {
            synchronized (this)
            {
                if (servlet != null && !containsServletHolder(servlet))
                    setServlets(ArrayUtil.addToArray(holders, servlet, ServletHolder.class));
            }

            ServletMapping mapping = new ServletMapping();
            mapping.setServletName(servlet.getName());
            mapping.setPathSpec(pathSpec);
            setServletMappings(ArrayUtil.addToArray(getServletMappings(), mapping, ServletMapping.class));
        }
        catch (RuntimeException e)
        {
            setServlets(holders);
            throw e;
        }
    }
