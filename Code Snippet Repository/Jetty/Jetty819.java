    @Test
    public void testStartupContext()
    {
        // Check Server for Handlers
        jetty.assertWebAppContextsExists("/bar", "/foo");

        File workDir = jetty.getJettyDir("workish");

        // Test for regressions
        assertDirNotExists("root of work directory",workDir,"webinf");
        assertDirNotExists("root of work directory",workDir,"jsp");

        // Test for correct behaviour
        assertTrue("Should have generated directory in work directory: " + workDir,hasJettyGeneratedPath(workDir,"foo.war"));
    }
