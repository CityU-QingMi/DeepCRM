    @Test
    public void testSpecWar() throws Exception
    {
        PreconfigureSpecWar.main(new String[]{});
        
        WebDescriptor descriptor = new WebDescriptor(Resource.newResource("./target/test-spec-preconfigured/WEB-INF/quickstart-web.xml"));
        descriptor.setValidating(true);
        descriptor.parse();
        Node node = descriptor.getRoot();
        assertThat(node,Matchers.notNullValue());
        
        System.setProperty("jetty.home", "target");
        
        //war file or dir to start
        String war = "target/test-spec-preconfigured";
        
        //optional jetty context xml file to configure the webapp
        Resource contextXml = Resource.newResource("src/test/resources/test-spec.xml");
        
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

        URL url = new URL("http://127.0.0.1:"+server.getBean(NetworkConnector.class).getLocalPort()+"/");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        assertEquals(200,connection.getResponseCode());
        assertThat(IO.toString((InputStream)connection.getContent()),Matchers.containsString("Test Specification WebApp"));
      
        server.stop();
    }
