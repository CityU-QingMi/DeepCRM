    public void testMirrorWildcardLookup()
    {
        Mirror mirrorA = newMirror( "a", "a", "http://a" );
        Mirror mirrorB = newMirror( "b", "b", "http://b" );
        Mirror mirrorC = newMirror( "c", "*", "http://wildcard" );

        List<Mirror> mirrors = Arrays.asList( mirrorA, mirrorB, mirrorC );

        assertSame( mirrorA, mirrorSelector.getMirror( getRepo( "a", "http://a.a" ), mirrors ) );

        assertSame( mirrorB, mirrorSelector.getMirror( getRepo( "b", "http://a.a" ), mirrors ) );

        assertSame( mirrorC, mirrorSelector.getMirror( getRepo( "c", "http://c.c" ), mirrors ) );
    }
