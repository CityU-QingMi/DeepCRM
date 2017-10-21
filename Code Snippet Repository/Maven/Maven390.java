    public void testPatterns()
    {
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "*" ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "*," ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), ",*," ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "*," ) );

        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "a" ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "a," ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), ",a," ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "a," ) );

        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "b" ), "a" ) );
        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "b" ), "a," ) );
        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "b" ), ",a" ) );
        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "b" ), ",a," ) );

        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "a,b" ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "b" ), "a,b" ) );

        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "c" ), "a,b" ) );

        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "*" ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "*,b" ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "*,!b" ) );

        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "*,!a" ) );
        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "a" ), "!a,*" ) );

        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "c" ), "*,!a" ) );
        assertTrue( DefaultMirrorSelector.matchPattern( getRepo( "c" ), "!a,*" ) );

        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "c" ), "!a,!c" ) );
        assertFalse( DefaultMirrorSelector.matchPattern( getRepo( "d" ), "!a,!c*" ) );
    }
