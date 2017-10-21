    @Test
    public void testConnectorTask() throws Exception
    {
        AntBuild build = new AntBuild(MavenTestingUtils.getTestResourceFile("connector-test.xml").getAbsolutePath());
      
        build.start();
        
        URI uri = new URI("http://" + build.getJettyHost() + ":" + build.getJettyPort());
        
        HttpURLConnection connection = (HttpURLConnection)uri.toURL().openConnection();
        
        connection.connect();

        assertThat("response code is 404", connection.getResponseCode(), is(404));

        build.stop();
    }
