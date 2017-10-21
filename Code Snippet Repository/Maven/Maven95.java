    public DefaultArtifactRepository( String id, String url, ArtifactRepositoryLayout layout,
                                      ArtifactRepositoryPolicy snapshots, ArtifactRepositoryPolicy releases )
    {
        super( id, url );

        this.layout = layout;

        if ( snapshots == null )
        {
            snapshots = new ArtifactRepositoryPolicy( true, ArtifactRepositoryPolicy.UPDATE_POLICY_ALWAYS,
                ArtifactRepositoryPolicy.CHECKSUM_POLICY_IGNORE );
        }

        this.snapshots = snapshots;

        if ( releases == null )
        {
            releases = new ArtifactRepositoryPolicy( true, ArtifactRepositoryPolicy.UPDATE_POLICY_ALWAYS,
                ArtifactRepositoryPolicy.CHECKSUM_POLICY_IGNORE );
        }

        this.releases = releases;
    }
