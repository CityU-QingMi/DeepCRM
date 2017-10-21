    public static String formatLocation( ModelProblem problem, String projectId )
    {
        StringBuilder buffer = new StringBuilder( 256 );

        if ( !problem.getModelId().equals( projectId ) )
        {
            buffer.append( problem.getModelId() );

            if ( problem.getSource().length() > 0 )
            {
                if ( buffer.length() > 0 )
                {
                    buffer.append( ", " );
                }
                buffer.append( problem.getSource() );
            }
        }

        if ( problem.getLineNumber() > 0 )
        {
            if ( buffer.length() > 0 )
            {
                buffer.append( ", " );
            }
            buffer.append( "line " ).append( problem.getLineNumber() );
        }

        if ( problem.getColumnNumber() > 0 )
        {
            if ( buffer.length() > 0 )
            {
                buffer.append( ", " );
            }
            buffer.append( "column " ).append( problem.getColumnNumber() );
        }

        return buffer.toString();
    }
