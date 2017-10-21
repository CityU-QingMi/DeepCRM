    public static List<Repository> mergeRepositoryLists( List<Repository> dominant, List<Repository> recessive )
    {
        List<Repository> repositories = new ArrayList<>();

        for ( Repository repository : dominant )
        {
            repositories.add( repository );
        }

        for ( Repository repository : recessive )
        {
            if ( !repositories.contains( repository ) )
            {
                repositories.add( repository );
            }
        }

        return repositories;
    }
