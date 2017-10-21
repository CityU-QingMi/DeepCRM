    public DependencyNode transformGraph( DependencyNode node, DependencyGraphTransformationContext context )
        throws RepositoryException
    {
        if ( findPlexusUtils( node ) == null )
        {
            Artifact pu = new DefaultArtifact( GID, AID, null, EXT, VER );
            DefaultDependencyNode child = new DefaultDependencyNode( new Dependency( pu, JavaScopes.RUNTIME ) );
            child.setRepositories( node.getRepositories() );
            child.setRequestContext( node.getRequestContext() );
            node.getChildren().add( child );
        }

        return node;
    }
