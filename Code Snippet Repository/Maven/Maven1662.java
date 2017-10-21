    protected void mergeResource_Filtering( Resource target, Resource source, boolean sourceDominant,
                                            Map<Object, Object> context )
    {
        String src = source.getFiltering();
        if ( src != null )
        {
            if ( sourceDominant || target.getFiltering() == null )
            {
                target.setFiltering( src );
                target.setLocation( "filtering", source.getLocation( "filtering" ) );
            }
        }
    }
