    protected void mergeDistributionManagement_DownloadUrl( DistributionManagement target,
                                                            DistributionManagement source, boolean sourceDominant,
                                                            Map<Object, Object> context )
    {
        String src = source.getDownloadUrl();
        if ( src != null )
        {
            if ( sourceDominant || target.getDownloadUrl() == null )
            {
                target.setDownloadUrl( src );
                target.setLocation( "downloadUrl", source.getLocation( "downloadUrl" ) );
            }
        }
    }
