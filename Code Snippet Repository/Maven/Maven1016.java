    private static String toMessage( List<Problem> problems )
    {
        StringWriter buffer = new StringWriter( 1024 );

        PrintWriter writer = new PrintWriter( buffer );

        writer.print( problems.size() );
        writer.print( ( problems.size() == 1 ) ? " problem was " : " problems were " );
        writer.print( "encountered while building the effective toolchains" );
        writer.println();

        for ( Problem problem : problems )
        {
            writer.print( "[" );
            writer.print( problem.getSeverity() );
            writer.print( "] " );
            writer.print( problem.getMessage() );
            String location = problem.getLocation();
            if ( !location.isEmpty() )
            {
                writer.print( " @ " );
                writer.print( location );
            }
            writer.println();
        }

        return buffer.toString();
    }
