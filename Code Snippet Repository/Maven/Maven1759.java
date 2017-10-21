    protected void mergeContributor_Timezone( Contributor target, Contributor source, boolean sourceDominant,
                                              Map<Object, Object> context )
    {
        String src = source.getTimezone();
        if ( src != null )
        {
            if ( sourceDominant || target.getTimezone() == null )
            {
                target.setTimezone( src );
                target.setLocation( "timezone", source.getLocation( "timezone" ) );
            }
        }
    }
