    private ExceptionSummary handle( String message, Throwable exception )
    {
        String reference = getReference( exception );

        List<ExceptionSummary> children = null;

        if ( exception instanceof ProjectBuildingException )
        {
            List<ProjectBuildingResult> results = ( (ProjectBuildingException) exception ).getResults();

            children = new ArrayList<>();

            for ( ProjectBuildingResult result : results )
            {
                ExceptionSummary child = handle( result );
                if ( child != null )
                {
                    children.add( child );
                }
            }

            message = "The build could not read " + children.size() + " project" + ( children.size() == 1 ? "" : "s" );
        }
        else
        {
            message = getMessage( message, exception );
        }

        return new ExceptionSummary( exception, message, reference, children );
    }
