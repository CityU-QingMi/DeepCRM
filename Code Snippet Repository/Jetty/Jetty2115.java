    @Override
    public void parse (Set<? extends Handler> handlers, URI[] uris)
    throws Exception
    {
        for (URI uri : uris)
        {
            Bundle associatedBundle = _uriToBundle.get(uri);
            if (associatedBundle == null)
            {
                if (!_alreadyParsed.add(uri))
                {
                    continue;
                }
                //a jar in WEB-INF/lib or the WEB-INF/classes
                //use the behavior of the super class for a standard jar.
                super.parse(handlers, new URI[] {uri});
            }
            else
            {
                parse(handlers, associatedBundle);
            }
        }
    }
