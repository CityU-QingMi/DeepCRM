    @Override
    public Resource getResource(String uriInContext) throws MalformedURLException
    {
        if (uriInContext==null || !uriInContext.startsWith(URIUtil.SLASH))
            throw new MalformedURLException(uriInContext);

        IOException ioe= null;
        Resource resource= null;
        int loop=0;
        while (uriInContext!=null && loop++<100)
        {
            try
            {
                resource= super.getResource(uriInContext);
                if (resource != null && resource.exists())
                    return resource;

                uriInContext = getResourceAlias(uriInContext);
            }
            catch (IOException e)
            {
                LOG.ignore(e);
                if (ioe==null)
                    ioe= e;
            }
        }

        if (ioe != null && ioe instanceof MalformedURLException)
            throw (MalformedURLException)ioe;

        return resource;
    }
