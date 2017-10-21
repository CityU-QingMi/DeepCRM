    private boolean isSafelyCacheable( RepositorySystemSession session, Artifact artifact )
    {
/**/
/**/
/**/
/**/

        WorkspaceReader workspace = session.getWorkspaceReader();
        if ( workspace == null )
        {
            return true;
        }

        Artifact pomArtifact = ArtifactDescriptorUtils.toPomArtifact( artifact );

        return workspace.findArtifact( pomArtifact ) == null;
    }
