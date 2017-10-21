    @Override
    public void destroyInstance (Object o)
        throws Exception
    {
        if (o==null)
            return;
        Filter f = (Filter)o;
        f.destroy();
        getServletHandler().destroyFilter(f);
    }
