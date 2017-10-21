    protected void mergeExtension_Version( Extension target, Extension source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        String src = source.getVersion();
        if ( src != null )
        {
            if ( sourceDominant || target.getVersion() == null )
            {
                target.setVersion( src );
                target.setLocation( "version", source.getLocation( "version" ) );
            }
        }
    }
