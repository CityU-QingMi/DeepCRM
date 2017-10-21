    private void manageArtifact( ResolutionNode node, ManagedVersionMap managedVersions,
                                 List<ResolutionListener> listeners )
    {
        Artifact artifact = managedVersions.get( node.getKey() );

        // Before we update the version of the artifact, we need to know
        // whether we are working on a transitive dependency or not. This
        // allows depMgmt to always override transitive dependencies, while
        // explicit child override depMgmt (viz. depMgmt should only
        // provide defaults to children, but should override transitives).
        // We can do this by calling isChildOfRootNode on the current node.
        if ( ( artifact.getVersion() != null )
                 && ( !node.isChildOfRootNode() || node.getArtifact().getVersion() == null ) )
        {
            fireEvent( ResolutionListener.MANAGE_ARTIFACT_VERSION, listeners, node, artifact );
            node.getArtifact().setVersion( artifact.getVersion() );
        }

        if ( ( artifact.getScope() != null ) && ( !node.isChildOfRootNode() || node.getArtifact().getScope() == null ) )
        {
            fireEvent( ResolutionListener.MANAGE_ARTIFACT_SCOPE, listeners, node, artifact );
            node.getArtifact().setScope( artifact.getScope() );
        }

        if ( Artifact.SCOPE_SYSTEM.equals( node.getArtifact().getScope() ) && ( node.getArtifact().getFile() == null )
                 && ( artifact.getFile() != null ) )
        {
            fireEvent( ResolutionListener.MANAGE_ARTIFACT_SYSTEM_PATH, listeners, node, artifact );
            node.getArtifact().setFile( artifact.getFile() );
        }
    }
