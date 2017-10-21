    protected void mergeScm_Connection( Scm target, Scm source, boolean sourceDominant, Map<Object, Object> context )
    {
        String src = source.getConnection();
        if ( src != null )
        {
            if ( sourceDominant || target.getConnection() == null )
            {
                target.setConnection( src );
                target.setLocation( "connection", source.getLocation( "connection" ) );
            }
        }
    }
