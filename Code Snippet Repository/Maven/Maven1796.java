    private static void removeMatchingRepository( Iterable<RemoteRepository> repositories, final String id )
    {
        Iterables.removeIf( repositories, new Predicate<RemoteRepository>()
        {
            @Override
            public boolean apply( RemoteRepository remoteRepository )
            {
                return remoteRepository.getId().equals( id );
            }
        } );
    }
