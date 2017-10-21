    public void testValidateMirror()
        throws Exception
    {
        Settings settings = new Settings();
        Mirror mirror = new Mirror();
        mirror.setId( "local" );
        settings.addMirror( mirror );
        mirror = new Mirror();
        mirror.setId( "illegal\\:/chars" );
        mirror.setUrl( "http://void" );
        mirror.setMirrorOf( "void" );
        settings.addMirror( mirror );

        SimpleProblemCollector problems = new SimpleProblemCollector();
        validator.validate( settings, problems );
        assertEquals( 4, problems.messages.size() );
        assertContains( problems.messages.get( 0 ), "'mirrors.mirror.id' must not be 'local'" );
        assertContains( problems.messages.get( 1 ), "'mirrors.mirror.url' for local is missing" );
        assertContains( problems.messages.get( 2 ), "'mirrors.mirror.mirrorOf' for local is missing" );
        assertContains( problems.messages.get( 3 ), "'mirrors.mirror.id' must not contain any of these characters" );
    }
