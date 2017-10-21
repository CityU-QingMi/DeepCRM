    protected void mergeOrganization_Url( Organization target, Organization source, boolean sourceDominant,
                                          Map<Object, Object> context )
    {
        String src = source.getUrl();
        if ( src != null )
        {
            if ( sourceDominant || target.getUrl() == null )
            {
                target.setUrl( src );
                target.setLocation( "url", source.getLocation( "url" ) );
            }
        }
    }
