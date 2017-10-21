    public void configureWebApplication () throws Exception
    {
        //As of jetty-7, you must use a <webApp> element
        if (webApp == null)
            webApp = new JettyWebAppContext();
        
        //Apply any context xml file to set up the webapp
        //CAUTION: if you've defined a <webApp> element then the
        //context xml file can OVERRIDE those settings
        if (contextXml != null)
        {
            File file = FileUtils.getFile(contextXml);
            XmlConfiguration xmlConfiguration = new XmlConfiguration(Resource.toURL(file));
            getLog().info("Applying context xml file "+contextXml);
            xmlConfiguration.configure(webApp);   
        }
        
        //If no contextPath was specified, go with default of project artifactid
        String cp = webApp.getContextPath();
        if (cp == null || "".equals(cp))
        {
            cp = "/"+project.getArtifactId();
            webApp.setContextPath(cp);
        }        

        //If no tmp directory was specified, and we have one, use it
        if (webApp.getTempDirectory() == null)
        {
            File target = new File(project.getBuild().getDirectory());
            File tmp = new File(target,"tmp");
            if (!tmp.exists())
                tmp.mkdirs();            
            webApp.setTempDirectory(tmp);
        }
      
        getLog().info("Context path = " + webApp.getContextPath());
        getLog().info("Tmp directory = "+ (webApp.getTempDirectory()== null? " determined at runtime": webApp.getTempDirectory()));
        getLog().info("Web defaults = "+(webApp.getDefaultsDescriptor()==null?" jetty default":webApp.getDefaultsDescriptor()));
        getLog().info("Web overrides = "+(webApp.getOverrideDescriptor()==null?" none":webApp.getOverrideDescriptor()));
    }
