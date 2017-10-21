    public static RepositorySystemSession overlay( ArtifactRepository repository, RepositorySystemSession session,
                                                   RepositorySystem system )
    {
        if ( repository == null || repository.getBasedir() == null )
        {
            return session;
        }

        if ( session != null )
        {
            LocalRepositoryManager lrm = session.getLocalRepositoryManager();
            if ( lrm != null && lrm.getRepository().getBasedir().equals( new File( repository.getBasedir() ) ) )
            {
                return session;
            }
        }
        else
        {
            session = new DefaultRepositorySystemSession();
        }

        final LocalRepositoryManager llrm = new LegacyLocalRepositoryManager( repository );

        return new DefaultRepositorySystemSession( session ).setLocalRepositoryManager( llrm );
    }
