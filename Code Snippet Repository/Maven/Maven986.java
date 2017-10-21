    @Override
    public List<String> findVersions( Artifact artifact )
    {
        Collection<String> versions = new LinkedHashSet<>();

        if ( buildReactor != null )
        {
            versions.addAll( buildReactor.findVersions( artifact ) );
        }

        if ( ideWorkspace != null )
        {
            versions.addAll( ideWorkspace.findVersions( artifact ) );
        }

        versions.addAll( userLocalArtifactRepository.findVersions( artifact ) );

        return Collections.unmodifiableList( new ArrayList<>( versions ) );
    }
