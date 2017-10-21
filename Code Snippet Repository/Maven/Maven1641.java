    protected void mergeBuildBase_FinalName( BuildBase target, BuildBase source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        String src = source.getFinalName();
        if ( src != null )
        {
            if ( sourceDominant || target.getFinalName() == null )
            {
                target.setFinalName( src );
                target.setLocation( "finalName", source.getLocation( "finalName" ) );
            }
        }
    }
