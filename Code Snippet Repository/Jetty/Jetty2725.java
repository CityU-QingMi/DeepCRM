    @Test
    public void testUncoveredHttpMethodDetection() throws Exception
    {
        _security.setAuthenticator(new BasicAuthenticator());
        _server.start();

       Set<String> paths =  _security.getPathsWithUncoveredHttpMethods();
       assertEquals(1, paths.size());
       assertEquals("/*", paths.iterator().next());
    }
