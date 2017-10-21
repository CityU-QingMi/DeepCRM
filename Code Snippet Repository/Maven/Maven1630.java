    protected void mergeBuild_TestSourceDirectory( Build target, Build source, boolean sourceDominant,
                                                   Map<Object, Object> context )
    {
        String src = source.getTestSourceDirectory();
        if ( src != null )
        {
            if ( sourceDominant || target.getTestSourceDirectory() == null )
            {
                target.setTestSourceDirectory( src );
                target.setLocation( "testSourceDirectory", source.getLocation( "testSourceDirectory" ) );
            }
        }
    }
