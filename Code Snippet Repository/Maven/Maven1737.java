    protected void mergeParent_RelativePath( Parent target, Parent source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        String src = source.getRelativePath();
        if ( src != null )
        {
            if ( sourceDominant || target.getRelativePath() == null )
            {
                target.setRelativePath( src );
                target.setLocation( "relativePath", source.getLocation( "relativePath" ) );
            }
        }
    }
