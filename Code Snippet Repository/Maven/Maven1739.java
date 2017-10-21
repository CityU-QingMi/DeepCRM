    protected void mergeOrganization_Name( Organization target, Organization source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        String src = source.getName();
        if ( src != null )
        {
            if ( sourceDominant || target.getName() == null )
            {
                target.setName( src );
                target.setLocation( "name", source.getLocation( "name" ) );
            }
        }
    }
