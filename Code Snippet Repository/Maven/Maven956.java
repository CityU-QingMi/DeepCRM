    private static boolean artifactEquals( Artifact a1, Artifact a2 )
    {
        if ( a1 == a2 )
        {
            return true;
        }

        return eq( a1.getGroupId(), a2.getGroupId() )
            && eq( a1.getArtifactId(), a2.getArtifactId() )
            && eq( a1.getType(), a2.getType() )
            && eq( a1.getVersion(), a2.getVersion() )
            && eq( a1.getClassifier(), a2.getClassifier() )
            && eq( a1.getScope(), a2.getScope() )
            && eq( a1.getDependencyFilter(), a2.getDependencyFilter() )
            && a1.isOptional() == a2.isOptional();
    }
