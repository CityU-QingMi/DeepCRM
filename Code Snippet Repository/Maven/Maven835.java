    public void removeBannedDependencies( Collection<Artifact> artifacts )
    {
        if ( !bannedArtifacts.isEmpty() && artifacts != null )
        {
            for ( Iterator<Artifact> it = artifacts.iterator(); it.hasNext(); )
            {
                Artifact artifact = it.next();
                if ( bannedArtifacts.containsKey( artifact ) )
                {
                    it.remove();
                }
            }
        }
    }
