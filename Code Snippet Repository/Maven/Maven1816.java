    public void merge( File existing, File result )
        throws RepositoryException
    {
        Metadata recessive = read( existing );

        merge( recessive );

        write( result, metadata );

        merged = true;
    }
