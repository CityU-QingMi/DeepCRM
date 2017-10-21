    protected void mergeBuild_SourceDirectory( Build target, Build source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        String src = source.getSourceDirectory();
        if ( src != null )
        {
            if ( sourceDominant || target.getSourceDirectory() == null )
            {
                target.setSourceDirectory( src );
                target.setLocation( "sourceDirectory", source.getLocation( "sourceDirectory" ) );
            }
        }
    }
