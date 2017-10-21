    protected Artifact getArtifactForOverlayConfig (OverlayConfig c, List<Artifact> wars)
    {
        if (wars == null || wars.isEmpty() || c == null)
            return null;

        Artifact war = null;
        Iterator<Artifact> itor = wars.iterator();
        while(itor.hasNext() && war == null)
        {
            Artifact a = itor.next();
            if (c.matchesArtifact(a.gid, a.aid, null))
                war = a;
        }
        return war;
    }
