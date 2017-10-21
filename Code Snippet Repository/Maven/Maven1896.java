    public void testValidateUniqueProxyId()
        throws Exception
    {
        Settings settings = new Settings();
        Proxy proxy = new Proxy();
        String id = null;
        proxy.setId( id );
        proxy.setHost("www.example.com");
        settings.addProxy( proxy );
        settings.addProxy( proxy );

        SimpleProblemCollector problems = new SimpleProblemCollector();
        validator.validate( settings, problems );
        assertEquals( 1, problems.messages.size() );
        assertContains( problems.messages.get( 0 ), "'proxies.proxy.id' must be unique"
            + " but found duplicate proxy with id " + id );

    }
