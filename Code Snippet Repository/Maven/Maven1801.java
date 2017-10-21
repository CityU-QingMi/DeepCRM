    private boolean resolve( VersionResult result, Map<String, VersionInfo> infos, String key )
    {
        VersionInfo info = infos.get( key );
        if ( info != null )
        {
            result.setVersion( info.version );
            result.setRepository( info.repository );
        }
        return info != null;
    }
