    public MavenArtifactRepository( String id, String url, ArtifactRepositoryLayout layout,
                                    ArtifactRepositoryPolicy snapshots, ArtifactRepositoryPolicy releases )
    {
        this.id = id;
        this.url = url;
        this.layout = layout;
        this.snapshots = snapshots;
        this.releases = releases;
        //
        // Derive these from the URL
        //
        this.protocol = protocol( url );
        this.basedir = basedir( url );
    }
