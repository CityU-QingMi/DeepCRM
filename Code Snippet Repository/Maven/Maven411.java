    public void testAuthenticationHandling()
        throws Exception
    {
        Server server = new Server();
        server.setId( "repository" );
        server.setUsername( "jason" );
        server.setPassword( "abc123" );

        ArtifactRepository repository =
            repositorySystem.createArtifactRepository( "repository", "http://foo", null, null, null );
        repositorySystem.injectAuthentication( Arrays.asList( repository ), Arrays.asList( server ) );
        Authentication authentication = repository.getAuthentication();
        assertNotNull( authentication );
        assertEquals( "jason", authentication.getUsername() );
        assertEquals( "abc123", authentication.getPassword() );
    }
