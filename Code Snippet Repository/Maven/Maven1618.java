    protected void mergeCiManagement_System( CiManagement target, CiManagement source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        String src = source.getSystem();
        if ( src != null )
        {
            if ( sourceDominant || target.getSystem() == null )
            {
                target.setSystem( src );
                target.setLocation( "system", source.getLocation( "system" ) );
            }
        }
    }
