    public void testArtifactMapByVersionlessIdOrdering()
        throws Exception
    {
        List<Artifact> list = new ArrayList<>();
        list.add( newArtifact( "b" ) );
        list.add( newArtifact( "a" ) );
        list.add( newArtifact( "c" ) );
        list.add( newArtifact( "e" ) );
        list.add( newArtifact( "d" ) );

        Map<String, Artifact> map = ArtifactUtils.artifactMapByVersionlessId( list );
        assertNotNull( map );
        assertEquals( list, new ArrayList<>( map.values() ) );
    }
