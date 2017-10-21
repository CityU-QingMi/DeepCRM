    public ArtifactMetadata( String name )
    {
        if ( name == null )
        {
            return;
        }
        int ind1 = name.indexOf( ':' );
        int ind2 = name.lastIndexOf( ':' );

        if ( ind1 == -1 || ind2 == -1 )
        {
            return;
        }

        this.groupId = name.substring( 0, ind1 );
        if ( ind1 == ind2 )
        {
            this.artifactId = name.substring( ind1 + 1 );
        }
        else
        {
            this.artifactId = name.substring( ind1 + 1, ind2 );
            this.version = name.substring( ind2 + 1 );
        }
    }
