    private static String toMessage( String modelId, List<ModelProblem> problems )
    {
        StringWriter buffer = new StringWriter( 1024 );

        PrintWriter writer = new PrintWriter( buffer );

        writer.print( problems.size() );
        writer.print( ( problems.size() == 1 ) ? " problem was " : " problems were " );
        writer.print( "encountered while building the effective model" );
        if ( modelId != null && modelId.length() > 0 )
        {
            writer.print( " for " );
            writer.print( modelId );
        }
        writer.println();

        for ( ModelProblem problem : problems )
        {
            writer.print( "[" );
            writer.print( problem.getSeverity() );
            writer.print( "] " );
            writer.print( problem.getMessage() );
            writer.print( " @ " );
            writer.println( ModelProblemUtils.formatLocation( problem, modelId ) );
        }

        return buffer.toString();
    }
