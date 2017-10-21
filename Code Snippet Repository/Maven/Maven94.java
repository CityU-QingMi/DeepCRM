    @Deprecated
    public ArtifactRepository getMirrorRepository( ArtifactRepository repository )
    {

        Mirror mirror = mirrorSelector.getMirror( repository, legacySupport.getSession().getSettings().getMirrors() );

        if ( mirror != null )
        {
            String id = mirror.getId();
            if ( id == null )
            {
                // TODO this should be illegal in settings.xml
                id = repository.getId();
            }

            log.debug( "Using mirror: " + mirror.getUrl() + " (id: " + id + ")" );

            repository = artifactRepositoryFactory.createArtifactRepository( id, mirror.getUrl(),
                                                                     repository.getLayout(), repository.getSnapshots(),
                                                                     repository.getReleases() );
        }
        return repository;
    }
