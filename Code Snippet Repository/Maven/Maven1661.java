    protected void mergeResource_TargetPath( Resource target, Resource source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        String src = source.getTargetPath();
        if ( src != null )
        {
            if ( sourceDominant || target.getTargetPath() == null )
            {
                target.setTargetPath( src );
                target.setLocation( "targetPath", source.getLocation( "targetPath" ) );
            }
        }
    }
