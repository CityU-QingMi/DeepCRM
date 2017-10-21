    private List<Artifact> getTrail()
        throws OverConstrainedVersionException
    {
        if ( trail == null )
        {
            List<Artifact> ids = new LinkedList<>();
            ResolutionNode node = this;
            while ( node != null )
            {
                Artifact artifact = node.getArtifact();
                if ( artifact.getVersion() == null )
                {
                    // set the recommended version
                    ArtifactVersion selected = artifact.getSelectedVersion();
                    // MNG-2123: null is a valid response to getSelectedVersion, don't
                    // assume it won't ever be.
                    if ( selected != null )
                    {
                        artifact.selectVersion( selected.toString() );
                    }
                    else
                    {
                        throw new OverConstrainedVersionException( "Unable to get a selected Version for "
                            + artifact.getArtifactId(), artifact );
                    }
                }

                ids.add( 0, artifact );
                node = node.parent;
            }
            trail = ids;
        }
        return trail;
    }
