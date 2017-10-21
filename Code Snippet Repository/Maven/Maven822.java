    private List<Artifact> toMavenArtifacts( DependencyNode root, PreorderNodeListGenerator nlg )
    {
        List<Artifact> artifacts = new ArrayList<>( nlg.getNodes().size() );
        RepositoryUtils.toArtifacts( artifacts, Collections.singleton( root ), Collections.<String>emptyList(), null );
        for ( Iterator<Artifact> it = artifacts.iterator(); it.hasNext(); )
        {
            Artifact artifact = it.next();
            if ( artifact.getFile() == null )
            {
                it.remove();
            }
        }
        return artifacts;
    }
