    protected void mergeContributor_Organization( Contributor target, Contributor source, boolean sourceDominant,
                                                  Map<Object, Object> context )
    {
        String src = source.getOrganization();
        if ( src != null )
        {
            if ( sourceDominant || target.getOrganization() == null )
            {
                target.setOrganization( src );
                target.setLocation( "organization", source.getLocation( "organization" ) );
            }
        }
    }
