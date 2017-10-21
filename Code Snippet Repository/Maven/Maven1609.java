    protected void mergeModel_Description( Model target, Model source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        String src = source.getDescription();
        if ( src != null )
        {
            if ( sourceDominant || target.getDescription() == null )
            {
                target.setDescription( src );
                target.setLocation( "description", source.getLocation( "description" ) );
            }
        }
    }
