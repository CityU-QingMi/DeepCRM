    public void testMirrorLookup()
    {
        Mirror mirrorA = newMirror( "a", "a", "http://a" );
        Mirror mirrorB = newMirror( "b", "b", "http://b" );

        List<Mirror> mirrors = Arrays.asList( mirrorA, mirrorB );

        assertSame( mirrorA, mirrorSelector.getMirror( getRepo( "a", "http://a.a" ), mirrors ) );

        assertSame( mirrorB, mirrorSelector.getMirror( getRepo( "b", "http://a.a" ), mirrors ) );

        assertNull( mirrorSelector.getMirror( getRepo( "c", "http://c.c" ), mirrors ) );
    }
