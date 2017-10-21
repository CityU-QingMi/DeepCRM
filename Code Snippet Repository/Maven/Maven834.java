    private DependencyNode findPlexusUtils( DependencyNode node )
    {
        Artifact artifact = node.getDependency().getArtifact();

        if ( AID.equals( artifact.getArtifactId() ) && GID.equals( artifact.getGroupId() )
            && EXT.equals( artifact.getExtension() ) && "".equals( artifact.getClassifier() ) )
        {
            return node;
        }

        for ( DependencyNode child : node.getChildren() )
        {
            DependencyNode result = findPlexusUtils( child );
            if ( result != null )
            {
                return result;
            }
        }

        return null;
    }
