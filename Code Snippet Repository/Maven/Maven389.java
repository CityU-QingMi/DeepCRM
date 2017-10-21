    public void testMirrorStopOnFirstMatch()
    {
        // exact matches win first
        Mirror mirrorA2 = newMirror( "a2", "a,b", "http://a2" );
        Mirror mirrorA = newMirror( "a", "a", "http://a" );
        // make sure repeated entries are skipped
        Mirror mirrorA3 = newMirror( "a", "a", "http://a3" );

        Mirror mirrorB = newMirror( "b", "b", "http://b" );
        Mirror mirrorC = newMirror( "c", "d,e", "http://de" );
        Mirror mirrorC2 = newMirror( "c", "*", "http://wildcard" );
        Mirror mirrorC3 = newMirror( "c", "e,f", "http://ef" );

        List<Mirror> mirrors = Arrays.asList( mirrorA2, mirrorA, mirrorA3, mirrorB, mirrorC, mirrorC2, mirrorC3 );

        assertSame( mirrorA, mirrorSelector.getMirror( getRepo( "a", "http://a.a" ), mirrors ) );

        assertSame( mirrorB, mirrorSelector.getMirror( getRepo( "b", "http://a.a" ), mirrors ) );

        assertSame( mirrorC2, mirrorSelector.getMirror( getRepo( "c", "http://c.c" ), mirrors ) );

        assertSame( mirrorC, mirrorSelector.getMirror( getRepo( "d", "http://d" ), mirrors ) );

        assertSame( mirrorC, mirrorSelector.getMirror( getRepo( "e", "http://e" ), mirrors ) );

        assertSame( mirrorC2, mirrorSelector.getMirror( getRepo( "f", "http://f" ), mirrors ) );
    }
