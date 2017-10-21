    private static Repository convertFromProfileXmlRepository( org.apache.maven.profiles.Repository profileXmlRepo )
    {
        Repository repo = new Repository();

        repo.setId( profileXmlRepo.getId() );
        repo.setLayout( profileXmlRepo.getLayout() );
        repo.setName( profileXmlRepo.getName() );
        repo.setUrl( profileXmlRepo.getUrl() );

        if ( profileXmlRepo.getSnapshots() != null )
        {
            repo.setSnapshots( convertRepositoryPolicy( profileXmlRepo.getSnapshots() ) );
        }
        if ( profileXmlRepo.getReleases() != null )
        {
            repo.setReleases( convertRepositoryPolicy( profileXmlRepo.getReleases() ) );
        }

        return repo;
    }
