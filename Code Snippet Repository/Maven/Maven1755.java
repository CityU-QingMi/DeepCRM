    protected void mergeContributor_Email( Contributor target, Contributor source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        String src = source.getEmail();
        if ( src != null )
        {
            if ( sourceDominant || target.getEmail() == null )
            {
                target.setEmail( src );
                target.setLocation( "email", source.getLocation( "email" ) );
            }
        }
    }
