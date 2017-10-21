    protected void mergeContributor_OrganizationUrl( Contributor target, Contributor source, boolean sourceDominant,
                                                     Map<Object, Object> context )
    {
        String src = source.getOrganizationUrl();
        if ( src != null )
        {
            if ( sourceDominant || target.getOrganizationUrl() == null )
            {
                target.setOrganizationUrl( src );
                target.setLocation( "organizationUrl", source.getLocation( "organizationUrl" ) );
            }
        }
    }
