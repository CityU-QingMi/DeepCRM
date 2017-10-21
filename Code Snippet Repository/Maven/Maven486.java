    private boolean attachedArtifactComparison( Artifact requested, Artifact attached )
    {
        //
        // We are taking as much as we can from the DefaultArtifact.equals(). The requested artifact has no file so
        // we want to remove that from the comparison.
        //
        return requested.getArtifactId().equals( attached.getArtifactId() )
            && requested.getGroupId().equals( attached.getGroupId() )
            && requested.getVersion().equals( attached.getVersion() )
            && requested.getExtension().equals( attached.getExtension() )
            && requested.getClassifier().equals( attached.getClassifier() );
    }
