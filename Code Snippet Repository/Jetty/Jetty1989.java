    @Override
    protected List<Resource> findJars (WebAppContext context)
    throws Exception
    {
        List<Resource> list = new ArrayList<>();
        JettyWebAppContext jwac = (JettyWebAppContext)context;
        List<File> files = jwac.getWebInfLib();
        if (files != null)
        {
            files.forEach( file -> {
                if (file.getName().toLowerCase(Locale.ENGLISH).endsWith(".jar")
                    || file.isDirectory())
                {
                    try
                    {
                        LOG.debug( " add  resource to resources to examine {}", file );
                        list.add(Resource.newResource(file.toURI()));
                    }
                    catch (Exception e)
                    {
                        LOG.warn("Bad url ", e);
                    }
                }
            } );
        }

        List<Resource> superList = super.findJars(context);
        if (superList != null)
            list.addAll(superList);
        return list;
    }
