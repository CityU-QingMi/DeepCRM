    protected void mergeBuild_TestOutputDirectory( Build target, Build source, boolean sourceDominant,
                                                   Map<Object, Object> context )
    {
        String src = source.getTestOutputDirectory();
        if ( src != null )
        {
            if ( sourceDominant || target.getTestOutputDirectory() == null )
            {
                target.setTestOutputDirectory( src );
                target.setLocation( "testOutputDirectory", source.getLocation( "testOutputDirectory" ) );
            }
        }
    }
