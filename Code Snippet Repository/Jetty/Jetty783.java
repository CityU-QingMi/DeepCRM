    protected void initializeWebAppContextDefaults(WebAppContext webapp)
    {
        if (_defaultsDescriptor != null)
            webapp.setDefaultsDescriptor(_defaultsDescriptor);
        webapp.setExtractWAR(_extractWars);
        webapp.setParentLoaderPriority(_parentLoaderPriority);
        if (_configurationClasses != null)
            webapp.setConfigurationClasses(_configurationClasses);

        if (_tempDirectory != null)
        {
/**/
/**/
/**/
/**/
/**/
/**/
            webapp.setAttribute(WebAppContext.BASETEMPDIR, _tempDirectory);
        }
    }
