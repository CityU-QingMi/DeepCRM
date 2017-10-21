    protected void mergeSite_Id( Site target, Site source, boolean sourceDominant, Map<Object, Object> context )
    {
        String src = source.getId();
        if ( src != null )
        {
            if ( sourceDominant || target.getId() == null )
            {
                target.setId( src );
                target.setLocation( "id", source.getLocation( "id" ) );
            }
        }
    }
