    protected void mergeDistributionManagement_Status( DistributionManagement target, DistributionManagement source,
                                                       boolean sourceDominant, Map<Object, Object> context )
    {
        String src = source.getStatus();
        if ( src != null )
        {
            if ( sourceDominant || target.getStatus() == null )
            {
                target.setStatus( src );
                target.setLocation( "status", source.getLocation( "status" ) );
            }
        }
    }
