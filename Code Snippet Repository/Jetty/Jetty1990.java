    @Override
    protected List<Resource> findClassDirs(WebAppContext context) throws Exception
    {
        List<Resource> list = new ArrayList<>();
        
        JettyWebAppContext jwac = (JettyWebAppContext)context;
        List<File> files = jwac.getWebInfClasses();
        if (files != null)
        {
            files.forEach( file -> {
                if (file.exists() && file.isDirectory())
                {
                    try
                    {
                        list.add(Resource.newResource(file.toURI()));
                    }
                    catch (Exception e)
                    {
                        LOG.warn("Bad url ", e);
                    }
                }
            } );

        }
        
        List<Resource> classesDirs = super.findClassDirs(context);
        if (classesDirs != null)
            list.addAll(classesDirs);
        return list;
    }
