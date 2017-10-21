    public static List<Option> configureJettyHomeAndPort(boolean ssl,String jettySelectorFileName)
    {
        File etc = new File(OS.separators("src/test/config/etc"));
        
        List<Option> options = new ArrayList<Option>();
        StringBuffer xmlConfigs = new StringBuffer();
        xmlConfigs.append(new File(etc, "jetty.xml").toURI());
        xmlConfigs.append(";");
        if (ssl)
        {
            xmlConfigs.append(new File(etc, "jetty-ssl.xml").toURI());
            xmlConfigs.append(";");
            xmlConfigs.append(new File(etc, "jetty-https.xml").toURI());
            xmlConfigs.append(";");

        }
        xmlConfigs.append(new File(etc, jettySelectorFileName).toURI());
        xmlConfigs.append(";");
        xmlConfigs.append(new File(etc, "jetty-deployer.xml").toURI());
        xmlConfigs.append(";");
        xmlConfigs.append(new File(etc, "jetty-testrealm.xml").toURI());

        options.add(systemProperty(OSGiServerConstants.MANAGED_JETTY_XML_CONFIG_URLS).value(xmlConfigs.toString()));
        options.add(systemProperty("jetty.http.port").value(String.valueOf(TestJettyOSGiBootCore.DEFAULT_HTTP_PORT)));
        options.add(systemProperty("jetty.ssl.port").value(String.valueOf(TestJettyOSGiBootCore.DEFAULT_SSL_PORT)));
        options.add(systemProperty("jetty.home").value(etc.getParentFile().getAbsolutePath()));
        options.add(systemProperty("jetty.base").value(etc.getParentFile().getAbsolutePath()));
        return options;
    }
