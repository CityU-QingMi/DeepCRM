    private static String format( LocalRepository localRepository, List<RemoteRepository> remoteRepositories )
    {
        String repos = "[";

        if ( localRepository != null )
        {
            repos += localRepository.getId() + " (" + localRepository.getBasedir() + ")";
        }

        if ( remoteRepositories != null && !remoteRepositories.isEmpty() )
        {
            for ( RemoteRepository repository : remoteRepositories )
            {
                repos += ", ";

                if ( repository != null )
                {
                    repos += repository.getId() + " (" + repository.getUrl() + ")";
                }
            }
        }

        repos += "]";

        return repos;
    }
