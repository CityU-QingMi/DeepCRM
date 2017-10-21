    @Test
    public void testJettyXml() throws Exception
    {
        URL url = SpringXmlConfigurationTest.class.getClassLoader().getResource("org/eclipse/jetty/spring/jetty.xml");
        XmlConfiguration configuration = new XmlConfiguration(url);

        Server server = (Server)configuration.configure();

        server.dumpStdErr();
    }
