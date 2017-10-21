    @Test
    public void testXmlConfigured() throws Exception
    {
        XmlConfiguredJetty jetty = null;
        try
        {
            jetty = new XmlConfiguredJetty(testdir);
            jetty.addConfiguration("jetty.xml");
            jetty.addConfiguration("jetty-http.xml");
            jetty.addConfiguration("jetty-deploymgr-contexts.xml");

            // Should not throw an Exception
            jetty.load();

            // Start it
            jetty.start();
        }
        finally
        {
            if (jetty != null)
            {
                try
                {
                    jetty.stop();
                }
                catch (Exception ignore)
                {
                    // ignore
                }
            }
        }
    }
