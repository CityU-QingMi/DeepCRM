    public void testMirrorLayoutConsideredForMatching()
    {
        ArtifactRepository repo = getRepo( "a" );

        Mirror mirrorA = newMirror( "a", "a", null, "http://a" );
        Mirror mirrorB = newMirror( "b", "a", "p2", "http://b" );

        Mirror mirrorC = newMirror( "c", "*", null, "http://c" );
        Mirror mirrorD = newMirror( "d", "*", "p2", "http://d" );

        assertSame( mirrorA, mirrorSelector.getMirror( repo, Arrays.asList( mirrorA ) ) );
        assertNull( mirrorSelector.getMirror( repo, Arrays.asList( mirrorB ) ) );

        assertSame( mirrorC, mirrorSelector.getMirror( repo, Arrays.asList( mirrorC ) ) );
        assertNull( mirrorSelector.getMirror( repo, Arrays.asList( mirrorD ) ) );
    }
