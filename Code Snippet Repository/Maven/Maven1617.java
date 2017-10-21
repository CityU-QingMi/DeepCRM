    protected void mergeScm_Tag( Scm target, Scm source, boolean sourceDominant, Map<Object, Object> context )
    {
        String src = source.getTag();
        if ( src != null )
        {
            if ( sourceDominant || target.getTag() == null )
            {
                target.setTag( src );
                target.setLocation( "tag", source.getLocation( "tag" ) );
            }
        }
    }
