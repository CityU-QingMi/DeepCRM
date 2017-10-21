    private ArtifactRepositoryLayout getLayout( String id )
    {
        ArtifactRepositoryLayout layout = layouts.get( id );

        if ( layout == null )
        {
            layout = new UnknownRepositoryLayout( id, layouts.get( "default" ) );
        }

        return layout;
    }
