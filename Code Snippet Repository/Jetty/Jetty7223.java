    public void setJettyStandardIdsAndProperties(Object server, Resource webapp)
    {
        try
        {
            if (server!=null)
                getIdMap().put("Server", server);
            
            Resource home = Resource.newResource(System.getProperty("jetty.home","."));
            getProperties().put("jetty.home",home.toString());
            getProperties().put("jetty.home.uri",normalizeURI(home.getURI().toString()));

            Resource base = Resource.newResource(System.getProperty("jetty.base",home.toString()));
            getProperties().put("jetty.base",base.toString());
            getProperties().put("jetty.base.uri",normalizeURI(base.getURI().toString()));

            if (webapp!=null)
            {
                getProperties().put("jetty.webapp",webapp.toString());
                getProperties().put("jetty.webapps",webapp.getFile().toPath().getParent().toString());
                getProperties().put("jetty.webapps.uri",normalizeURI(webapp.getURI().toString()));
            }
        }
        catch(Exception e)
        {
            LOG.warn(e);
        }
    }
