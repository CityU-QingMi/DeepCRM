    public void deployWebapp(WebAppContext webapp) throws Exception
    {
        contexts.addHandler(webapp);
        contexts.manage(webapp);
        webapp.start();
        if (LOG.isDebugEnabled())
        {
            webapp.dump(System.err);
        }
    }
