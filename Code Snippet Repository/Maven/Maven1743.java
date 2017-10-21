    protected void mergeLicense_Distribution( License target, License source, boolean sourceDominant,
                                              Map<Object, Object> context )
    {
        String src = source.getDistribution();
        if ( src != null )
        {
            if ( sourceDominant || target.getDistribution() == null )
            {
                target.setDistribution( src );
                target.setLocation( "distribution", source.getLocation( "distribution" ) );
            }
        }
    }
