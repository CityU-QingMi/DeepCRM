    private ExceptionSummary handle( ModelProblem problem, String projectId )
    {
        if ( ModelProblem.Severity.ERROR.compareTo( problem.getSeverity() ) >= 0 )
        {
            String message = problem.getMessage();

            String location = ModelProblemUtils.formatLocation( problem, projectId );

            if ( StringUtils.isNotEmpty( location ) )
            {
                message += " @ " + location;
            }

            return handle( message, problem.getException() );
        }
        else
        {
            return null;
        }
    }
