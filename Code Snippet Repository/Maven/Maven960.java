    public ResolutionGroup get( Artifact artifact, boolean resolveManagedVersions, ArtifactRepository localRepository,
                                List<ArtifactRepository> remoteRepositories )
    {
        CacheKey cacheKey = newCacheKey( artifact, resolveManagedVersions, localRepository, remoteRepositories );

        CacheRecord cacheRecord = cache.get( cacheKey );

        if ( cacheRecord != null && !cacheRecord.isStale() )
        {
            Artifact pomArtifact = ArtifactUtils.copyArtifact( cacheRecord.getArtifact() );
            Artifact relocatedArtifact = ArtifactUtils.copyArtifactSafe( cacheRecord.getRelocatedArtifact() );
            Set<Artifact> artifacts =
                ArtifactUtils.copyArtifacts( cacheRecord.getArtifacts(), new LinkedHashSet<Artifact>() );
            Map<String, Artifact> managedVersions = cacheRecord.getManagedVersions();
            if ( managedVersions != null )
            {
                managedVersions = ArtifactUtils.copyArtifacts( managedVersions, new LinkedHashMap<String, Artifact>() );
            }
            return new ResolutionGroup( pomArtifact, relocatedArtifact, artifacts, managedVersions,
                                        cacheRecord.getRemoteRepositories() );
        }

        cache.remove( cacheKey );

        return null;
    }
