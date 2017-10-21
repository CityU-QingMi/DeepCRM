    public AttachedArtifact( Artifact parent, String type, String classifier, ArtifactHandler artifactHandler )
    {
        super( parent.getGroupId(), parent.getArtifactId(), parent.getVersionRange(), parent.getScope(), type,
               classifier, artifactHandler, parent.isOptional() );

        setDependencyTrail( Collections.singletonList( parent.getId() ) );

        this.parent = parent;

        if ( getId().equals( parent.getId() ) )
        {
            throw new InvalidArtifactRTException( parent.getGroupId(), parent.getArtifactId(), parent.getVersion(),
                                                  parent.getType(), "An attached artifact must have a different ID"
                                                      + " than its corresponding main artifact." );
        }
    }
