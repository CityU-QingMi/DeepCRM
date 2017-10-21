    protected void mergeDistributionManagement_Site( DistributionManagement target, DistributionManagement source,
                                                     boolean sourceDominant, Map<Object, Object> context )
    {
        Site src = source.getSite();
        if ( src != null )
        {
            Site tgt = target.getSite();
            if ( tgt == null )
            {
                tgt = new Site();
                target.setSite( tgt );
            }
            mergeSite( tgt, src, sourceDominant, context );
        }
    }
