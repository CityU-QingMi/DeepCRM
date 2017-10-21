    private VersionsMetadataGenerator( RepositorySystemSession session, Collection<? extends Metadata> metadatas )
    {
        versions = new LinkedHashMap<>();
        processedVersions = new LinkedHashMap<>();

/**/
/**/
/**/
/**/
/**/
/**/
        for ( Iterator<? extends Metadata> it = metadatas.iterator(); it.hasNext(); )
        {
            Metadata metadata = it.next();
            if ( metadata instanceof VersionsMetadata )
            {
                it.remove();
                VersionsMetadata versionsMetadata = (VersionsMetadata) metadata;
                processedVersions.put( versionsMetadata.getKey(), versionsMetadata );
            }
        }
    }
