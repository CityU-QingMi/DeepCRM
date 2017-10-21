    protected void mergeParent_GroupId( Parent target, Parent source, boolean sourceDominant,
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
