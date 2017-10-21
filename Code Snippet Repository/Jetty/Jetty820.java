    @Test
    public void testStartupSymlinkContext()
    {
        assumeTrue(symlinkSupported);
        
        // Check for path
        File barLink = jetty.getJettyDir("webapps/bar.war");
        assertTrue("bar.war link exists: " + barLink.toString(), barLink.exists());
        assertTrue("bar.war link isFile: " + barLink.toString(), barLink.isFile());
        
        // Check Server for expected Handlers
        jetty.assertWebAppContextsExists("/bar", "/foo");
        
        // Test for expected work/temp directory behaviour
        File workDir = jetty.getJettyDir("workish");
        assertTrue("Should have generated directory in work directory: " + workDir,hasJettyGeneratedPath(workDir,"bar.war"));
    }
