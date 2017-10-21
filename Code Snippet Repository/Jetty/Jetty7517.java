    @Test
    public void testStandardTestWar() throws Exception
    {
        PreconfigureStandardTestWar.main(new String[]{});
        
        WebDescriptor descriptor = new WebDescriptor(Resource.newResource("./target/test-standard-preconfigured/WEB-INF/quickstart-web.xml"));
        descriptor.setValidating(true);
        descriptor.parse();
        Node node = descriptor.getRoot();
        assertThat(node,Matchers.notNullValue());
        
        System.setProperty("jetty.home", "target");
        
        //war file or dir to start
        String war = "target/test-standard-preconfigured";
        
        //optional jetty context xml file to configure the webapp
        Resource contextXml = Resource.newResource("src/test/resources/test.xml");
        
        Server server = new Server(0);
        
        QuickStartWebApp webapp = new QuickStartWebApp();
        webapp.setAutoPreconfigure(true);
        webapp.setWar(war);
        webapp.setContextPath("/");

        //apply context xml file
        if (contextXml != null)
        {
            // System.err.println("Applying "+contextXml);
            XmlConfiguration xmlConfiguration = new XmlConfiguration(contextXml.getURL());  
            xmlConfiguration.configure(webapp);   
        }
        
        server.setHandler(webapp);

        server.start();

        URL url = new URL("http://127.0.0.1:"+server.getBean(NetworkConnector.class).getLocalPort()+"/test/dump/info");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        assertEquals(200,connection.getResponseCode());
        assertThat(IO.toString((InputStream)connection.getContent()),Matchers.containsString("Dump Servlet"));
      
        server.stop();
    }
