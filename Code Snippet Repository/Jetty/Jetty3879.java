    @Deprecated
    public List<Decorator> getDecorators()
    {
        List<Decorator> ret = new ArrayList<ServletContextHandler.Decorator>();
        for (org.eclipse.jetty.util.Decorator decorator : _objFactory)
        {
            ret.add(new LegacyDecorator(decorator));
        }
        return Collections.unmodifiableList(ret);
    }
