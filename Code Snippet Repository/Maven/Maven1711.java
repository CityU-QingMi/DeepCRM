    protected void mergeDependency_GroupId( Dependency target, Dependency source, boolean sourceDominant,
                                            Map<Object, Object> context )
    {
        String src = source.getGroupId();
        if ( src != null )
        {
            if ( sourceDominant || target.getGroupId() == null )
            {
                target.setGroupId( src );
                target.setLocation( "groupId", source.getLocation( "groupId" ) );
            }
        }
    }
