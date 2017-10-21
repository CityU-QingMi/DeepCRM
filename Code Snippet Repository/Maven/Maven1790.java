    public static ModelCache newInstance( RepositorySystemSession session )
    {
        if ( session.getCache() == null )
        {
            return null;
        }
        else
        {
            return new DefaultModelCache( session );
        }
    }
