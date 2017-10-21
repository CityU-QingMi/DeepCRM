    protected void mergeDependency_Type( Dependency target, Dependency source, boolean sourceDominant,
                                         Map<Object, Object> context )
    {
        String src = source.getType();
        if ( src != null )
        {
            if ( sourceDominant || target.getType() == null )
            {
                target.setType( src );
                target.setLocation( "type", source.getLocation( "type" ) );
            }
        }
    }
