    private static String toMessage( List<SettingsProblem> problems )
    {
        StringWriter buffer = new StringWriter( 1024 );

        PrintWriter writer = new PrintWriter( buffer );

        writer.print( problems.size() );
        writer.print( ( problems.size() == 1 ) ? " problem was " : " problems were " );
        writer.print( "encountered while building the effective settings" );
        writer.println();

        for ( SettingsProblem problem : problems )
        {
            writer.print( "[" );
            writer.print( problem.getSeverity() );
            writer.print( "] " );
            writer.print( problem.getMessage() );
            writer.print( " @ " );
            writer.println( problem.getLocation() );
        }

        return buffer.toString();
    }
