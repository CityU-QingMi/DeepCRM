    protected void mergeBuildBase_DefaultGoal( BuildBase target, BuildBase source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        String src = source.getDefaultGoal();
        if ( src != null )
        {
            if ( sourceDominant || target.getDefaultGoal() == null )
            {
                target.setDefaultGoal( src );
                target.setLocation( "defaultGoal", source.getLocation( "defaultGoal" ) );
            }
        }
    }
