    protected void mergeBuildBase_Directory( BuildBase target, BuildBase source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        String src = source.getDirectory();
        if ( src != null )
        {
            if ( sourceDominant || target.getDirectory() == null )
            {
                target.setDirectory( src );
                target.setLocation( "directory", source.getLocation( "directory" ) );
            }
        }
    }
