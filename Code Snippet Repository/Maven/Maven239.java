    public ArtifactRepository createArtifactRepository( String id, String url,
                                                        ArtifactRepositoryLayout repositoryLayout,
                                                        ArtifactRepositoryPolicy snapshots,
                                                        ArtifactRepositoryPolicy releases )
    {
        if ( snapshots == null )
        {
            snapshots = new ArtifactRepositoryPolicy();
        }

        if ( releases == null )
        {
            releases = new ArtifactRepositoryPolicy();
        }

        if ( globalUpdatePolicy != null )
        {
            snapshots.setUpdatePolicy( globalUpdatePolicy );
            releases.setUpdatePolicy( globalUpdatePolicy );
        }

        if ( globalChecksumPolicy != null )
        {
            snapshots.setChecksumPolicy( globalChecksumPolicy );
            releases.setChecksumPolicy( globalChecksumPolicy );
        }

        ArtifactRepository repository;
        if ( repositoryLayout instanceof ArtifactRepositoryLayout2 )
        {
            repository =
                ( (ArtifactRepositoryLayout2) repositoryLayout ).newMavenArtifactRepository( id, url, snapshots,
                                                                                             releases );
        }
        else
        {
            repository = new MavenArtifactRepository( id, url, repositoryLayout, snapshots, releases );
        }

        return repository;
    }
