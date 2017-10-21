    @Test
    public void testWebApp () throws Exception
    {
        AntBuild build = new AntBuild(MavenTestingUtils.getTestResourceFile("webapp-test.xml").getAbsolutePath());

        build.start();

        URI uri = new URI("http://" + build.getJettyHost() + ":" + build.getJettyPort() + "/");

        HttpURLConnection connection = (HttpURLConnection)uri.toURL().openConnection();

        connection.connect();

        assertThat("response code is 200", connection.getResponseCode(), is(200));

        System.err.println("Stop build!");
        build.stop();
    }
