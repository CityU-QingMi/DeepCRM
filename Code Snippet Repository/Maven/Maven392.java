    public void testLayoutPattern()
    {
        assertTrue( DefaultMirrorSelector.matchesLayout( "default", null ) );
        assertTrue( DefaultMirrorSelector.matchesLayout( "default", "" ) );
        assertTrue( DefaultMirrorSelector.matchesLayout( "default", "*" ) );

        assertTrue( DefaultMirrorSelector.matchesLayout( "default", "default" ) );
        assertFalse( DefaultMirrorSelector.matchesLayout( "default", "legacy" ) );

        assertTrue( DefaultMirrorSelector.matchesLayout( "default", "legacy,default" ) );
        assertTrue( DefaultMirrorSelector.matchesLayout( "default", "default,legacy" ) );

        assertFalse( DefaultMirrorSelector.matchesLayout( "default", "legacy,!default" ) );
        assertFalse( DefaultMirrorSelector.matchesLayout( "default", "!default,legacy" ) );

        assertFalse( DefaultMirrorSelector.matchesLayout( "default", "*,!default" ) );
        assertFalse( DefaultMirrorSelector.matchesLayout( "default", "!default,*" ) );
    }
