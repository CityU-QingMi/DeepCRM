    protected void mergeRelocation_Message( Relocation target, Relocation source, boolean sourceDominant,
                                            Map<Object, Object> context )
    {
        String src = source.getMessage();
        if ( src != null )
        {
            if ( sourceDominant || target.getMessage() == null )
            {
                target.setMessage( src );
                target.setLocation( "message", source.getLocation( "message" ) );
            }
        }
    }
