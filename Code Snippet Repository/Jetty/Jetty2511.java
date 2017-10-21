    public static void preconfigure(Resource war, Resource dir, Resource xml) throws Exception 
    {
        // Do we need to unpack a war?
        if (war != null)
        {
            if (war.isDirectory())
                error("war file is directory");

            if (!dir.exists())
                dir.getFile().mkdirs();
            JarResource.newJarResource(war).copyTo(dir.getFile());
        }
        
        final Server server = new Server();

        QuickStartWebApp webapp = new QuickStartWebApp();

        if (xml != null)
        {
            if (xml.isDirectory() || !xml.toString().toLowerCase(Locale.ENGLISH).endsWith(".xml"))
                error("Bad context.xml: "+xml);
            XmlConfiguration xmlConfiguration = new XmlConfiguration(xml.getURL());
            xmlConfiguration.configure(webapp);
        }
        webapp.setResourceBase(dir.getFile().getAbsolutePath());
        webapp.setPreconfigure(true);
        server.setHandler(webapp);
        server.start();
        server.stop();
    }
