    public void addDependencies( Set<Artifact> artifacts, List<ArtifactRepository> remoteRepositories,
                                 ArtifactFilter filter )
        throws CyclicDependencyException, OverConstrainedVersionException
    {
        if ( artifacts != null && !artifacts.isEmpty() )
        {
            children = new ArrayList<>( artifacts.size() );

            for ( Artifact a : artifacts )
            {
                if ( parents.contains( a.getDependencyConflictId() ) )
                {
                    a.setDependencyTrail( getDependencyTrail() );

                    throw new CyclicDependencyException( "A dependency has introduced a cycle", a );
                }

                children.add( new ResolutionNode( a, remoteRepositories, this ) );
            }
        }
        else
        {
            children = Collections.emptyList();
        }
        trail = null;
    }
