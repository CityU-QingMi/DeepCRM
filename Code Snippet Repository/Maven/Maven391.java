    public void testPatternsWithExternal()
    {
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a", "http://localhost" ), "*" ) );
        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "a", "http://localhost" ), "external:*" ) );

        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a", "http://localhost" ), "external:*,a" ) );
        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "a", "http://localhost" ), "external:*,!a" ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a", "http://localhost" ), "a,external:*" ) );
        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "a", "http://localhost" ), "!a,external:*" ) );

        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "c", "http://localhost" ), "!a,external:*" ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "c", "http://somehost" ), "!a,external:*" ) );
    }
