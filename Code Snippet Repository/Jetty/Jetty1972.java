    protected Artifact getArtifactForOverlay (OverlayConfig o, List<Artifact> warArtifacts)
    {
        if (o == null || warArtifacts == null || warArtifacts.isEmpty())
            return null;
        
        for (Artifact a:warArtifacts)
        {
            if (o.matchesArtifact (a.getGroupId(), a.getArtifactId(), a.getClassifier()))
            {
               return a;
            }
        }
        
        return null;
    }
