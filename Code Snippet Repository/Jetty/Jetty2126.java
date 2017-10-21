    public String[] getConfigurationClasses()
    {
        if (_configurationClasses != null)
            return _configurationClasses;

        Configuration.ClassList defaults = Configuration.ClassList.serverDefault(_serverWrapper.getServer());

        //add before JettyWebXmlConfiguration
        if (annotationsAvailable())
            defaults.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", 
                               "org.eclipse.jetty.osgi.annotations.AnnotationConfiguration");

        //add in EnvConfiguration and PlusConfiguration just after FragmentConfiguration
        if (jndiAvailable())
            defaults.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
                              "org.eclipse.jetty.plus.webapp.EnvConfiguration",
                              "org.eclipse.jetty.plus.webapp.PlusConfiguration");
       String[] asArray = new String[defaults.size()];
       return defaults.toArray(asArray);
    }
