    private void merge( Map<String, VersionInfo> infos, String srcKey, String dstKey )
    {
        VersionInfo srcInfo = infos.get( srcKey );
        VersionInfo dstInfo = infos.get( dstKey );

        if ( dstInfo == null || ( srcInfo != null && dstInfo.isOutdated( srcInfo.timestamp )
            && srcInfo.repository != dstInfo.repository ) )
        {
            infos.put( dstKey, srcInfo );
        }
    }
