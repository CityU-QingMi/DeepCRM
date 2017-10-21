    protected static String constructArtifactPath( List<String> path,
                                                   String indentation )
    {
        StringBuilder sb = new StringBuilder();

        if ( path != null )
        {
            sb.append( LS );
            sb.append( indentation );
            sb.append( "Path to dependency: " );
            sb.append( LS );
            int num = 1;
            for ( Iterator<String> i = path.iterator(); i.hasNext(); num++ )
            {
                sb.append( indentation );
                sb.append( '\t' );
                sb.append( num );
                sb.append( ") " );
                sb.append( i.next() );
                sb.append( LS );
            }
        }

        return sb.toString();
    }
