    protected void mergeRepositoryBase_Layout( RepositoryBase target, RepositoryBase source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        String src = source.getLayout();
        if ( src != null )
        {
            if ( sourceDominant || target.getLayout() == null )
            {
                target.setLayout( src );
                target.setLocation( "layout", source.getLocation( "layout" ) );
            }
        }
    }
