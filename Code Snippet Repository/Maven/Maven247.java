    public ResolutionNode resolveConflict( ResolutionNode node1, ResolutionNode node2 )
    {
        try
        {
            ArtifactVersion version1 = node1.getArtifact().getSelectedVersion();
            ArtifactVersion version2 = node2.getArtifact().getSelectedVersion();

            return version1.compareTo( version2 ) <= 0 ? node1 : node2;
        }
        catch ( OverConstrainedVersionException exception )
        {
            // TODO log message or throw exception?

            return null;
        }
    }
