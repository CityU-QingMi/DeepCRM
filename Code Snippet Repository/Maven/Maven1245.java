    public void testCacheKey()
        throws Exception
    {
        Artifact a1 = repositorySystem.createArtifact( "testGroup", "testArtifact", "1.2.3", "jar" );
        @SuppressWarnings( "deprecation" )
        ArtifactRepository lr1 = new DelegatingLocalArtifactRepository( repositorySystem.createDefaultLocalRepository() );
        ArtifactRepository rr1 = repositorySystem.createDefaultRemoteRepository();
        a1.setDependencyFilter( new ExcludesArtifactFilter( Arrays.asList( "foo" ) ) );

        Artifact a2 = repositorySystem.createArtifact( "testGroup", "testArtifact", "1.2.3", "jar" );
        @SuppressWarnings( "deprecation" )
        ArtifactRepository lr2 = new DelegatingLocalArtifactRepository( repositorySystem.createDefaultLocalRepository() );
        ArtifactRepository rr2 = repositorySystem.createDefaultRemoteRepository();
        a2.setDependencyFilter( new ExcludesArtifactFilter( Arrays.asList( "foo" ) ) );

        // sanity checks
        assertNotSame( a1, a2 );
        assertNotSame( lr1, lr2 );
        assertNotSame( rr1, rr2 );

        CacheKey k1 = new CacheKey( a1, false, lr1, Collections.singletonList( rr1 ) );
        CacheKey k2 = new CacheKey( a2, false, lr2, Collections.singletonList( rr2 ) );

        assertEquals(k1.hashCode(), k2.hashCode());
    }
