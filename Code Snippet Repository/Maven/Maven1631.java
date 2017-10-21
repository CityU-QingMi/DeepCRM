    protected void mergeModel_InceptionYear( Model target, Model source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        String src = source.getInceptionYear();
        if ( src != null )
        {
            if ( sourceDominant || target.getInceptionYear() == null )
            {
                target.setInceptionYear( src );
                target.setLocation( "inceptionYear", source.getLocation( "inceptionYear" ) );
            }
        }
    }
