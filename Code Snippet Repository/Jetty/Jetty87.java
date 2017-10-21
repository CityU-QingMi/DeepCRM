    public void parse (final Set<? extends Handler> handlers, final URI[] uris) throws Exception
    {
        if (uris==null)
            return;

        MultiException me = new MultiException();
        
        for (URI uri:uris)
        {
            try
            {
                parse(handlers, uri);
            }
            catch (Exception e)
            {
                me.add(new RuntimeException("Problem parsing classes from "+ uri, e));
            }
        }
        me.ifExceptionThrow();
    }
