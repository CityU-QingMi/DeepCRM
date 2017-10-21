    protected void mergePlugin_Extensions( Plugin target, Plugin source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        String src = source.getExtensions();
        if ( src != null )
        {
            if ( sourceDominant || target.getExtensions() == null )
            {
                target.setExtensions( src );
                target.setLocation( "extensions", source.getLocation( "extensions" ) );
            }
        }
    }
