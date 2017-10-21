    public Collection<? extends Metadata> finish( Collection<? extends Artifact> artifacts )
    {
        for ( Artifact artifact : artifacts )
        {
            Object key = VersionsMetadata.getKey( artifact );
            if ( processedVersions.get( key ) == null )
            {
                VersionsMetadata versionsMetadata = versions.get( key );
                if ( versionsMetadata == null )
                {
                    versionsMetadata = new VersionsMetadata( artifact );
                    versions.put( key, versionsMetadata );
                }
            }
        }

        return versions.values();
    }
