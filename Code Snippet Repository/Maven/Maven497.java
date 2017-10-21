    public static String getLayout( ArtifactRepository repo )
    {
        try
        {
            return repo.getLayout().getId();
        }
        catch ( LinkageError e )
        {
/**/
/**/
/**/
            String className = repo.getLayout().getClass().getSimpleName();
            if ( className.endsWith( "RepositoryLayout" ) )
            {
                String layout = className.substring( 0, className.length() - "RepositoryLayout".length() );
                if ( layout.length() > 0 )
                {
                    layout = Character.toLowerCase( layout.charAt( 0 ) ) + layout.substring( 1 );
                    return layout;
                }
            }
            return "";
        }
    }
