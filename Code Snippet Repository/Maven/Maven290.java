    public MetadataTreeNode( ArtifactMetadata md, MetadataTreeNode parent, boolean resolved, ArtifactScopeEnum scope )
    {
        if ( md != null )
        {
            md.setArtifactScope( ArtifactScopeEnum.checkScope( scope ) );
            md.setResolved( resolved );
        }

        this.md = md;
        this.parent = parent;
    }
