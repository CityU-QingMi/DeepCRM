    protected void mergeRelocation_Version( Relocation target, Relocation source, boolean sourceDominant,
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
