    private ArtifactFilter newSubFilter()
    {
        return new ArtifactFilter()
        {
            public boolean include( Artifact artifact )
            {
                return false;
            }
        };
    }
