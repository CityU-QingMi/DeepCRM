    public DefaultHandler()
    {
        try
        {
            URL fav = this.getClass().getClassLoader().getResource("org/eclipse/jetty/favicon.ico");
            if (fav!=null)
            {
                Resource r = Resource.newResource(fav);
                _favicon=IO.readBytes(r.getInputStream());
            }
        }
        catch(Exception e)
        {
            LOG.warn(e);
        }
    }
