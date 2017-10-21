    public void testValidateUniqueServerId()
        throws Exception
    {
        Settings settings = new Settings();
        Server server1 = new Server();
        server1.setId( "test" );
        settings.addServer( server1 );
        Server server2 = new Server();
        server2.setId( "test" );
        settings.addServer( server2 );

        SimpleProblemCollector problems = new SimpleProblemCollector();
        validator.validate( settings, problems );
        assertEquals( 1, problems.messages.size() );
        assertContains( problems.messages.get( 0 ),
                        "'servers.server.id' must be unique but found duplicate server with id test" );
    }
