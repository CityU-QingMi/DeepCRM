    protected void mergeRepositoryBase_Id( RepositoryBase target, RepositoryBase source, boolean sourceDominant,
                                           Map<Object, Object> context )
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
