    private static String createMessage( List<ProjectBuildingResult> results )
    {
        StringWriter buffer = new StringWriter( 1024 );
        PrintWriter writer = new PrintWriter( buffer );
        writer.println( "Some problems were encountered while processing the POMs:" );
        try
        {

            for ( ProjectBuildingResult result : results )
            {
                for ( ModelProblem problem : result.getProblems() )
                {
                    writer.print( "[" );
                    writer.print( problem.getSeverity() );
                    writer.print( "] " );
                    writer.print( problem.getMessage() );
                    writer.print( " @ " );
                    writer.println( ModelProblemUtils.formatLocation( problem, result.getProjectId() ) );
                }
            }
        }
        finally
        {
            writer.close();
        }
        return buffer.toString();
    }
