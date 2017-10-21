    protected void mergePrerequisites_Maven( Prerequisites target, Prerequisites source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        String src = source.getMaven();
        if ( src != null )
        {
            if ( sourceDominant || target.getMaven() == null )
            {
                target.setMaven( src );
                target.setLocation( "maven", source.getLocation( "maven" ) );
            }
        }
    }
