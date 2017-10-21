    public void testValidateProxy()
        throws Exception
    {
        Settings settings = new Settings();
        Proxy proxy1 = new Proxy();
        settings.addProxy( proxy1 );

        SimpleProblemCollector problems = new SimpleProblemCollector();
        validator.validate( settings, problems );
        assertEquals( 1, problems.messages.size() );
        assertContains( problems.messages.get( 0 ), "'proxies.proxy.host' for default is missing" );
    }
