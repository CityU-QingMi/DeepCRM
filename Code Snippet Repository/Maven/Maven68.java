    public void testContains() throws InvalidVersionSpecificationException
    {
        ArtifactVersion actualVersion = new DefaultArtifactVersion( "2.0.5" );
        assertTrue( enforceVersion( "2.0.5", actualVersion ) );
        assertTrue( enforceVersion( "2.0.4", actualVersion ) );
        assertTrue( enforceVersion( "[2.0.5]", actualVersion ) );
        assertFalse( enforceVersion( "[2.0.6,)", actualVersion ) );
        assertFalse( enforceVersion( "[2.0.6]", actualVersion ) );
        assertTrue( enforceVersion( "[2.0,2.1]", actualVersion ) );
        assertFalse( enforceVersion( "[2.0,2.0.3]", actualVersion ) );
        assertTrue( enforceVersion( "[2.0,2.0.5]", actualVersion ) );
        assertFalse( enforceVersion( "[2.0,2.0.5)", actualVersion ) );
    }
