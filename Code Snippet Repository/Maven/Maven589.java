    private ExceptionSummary handle( ProjectBuildingResult result )
    {
        List<ExceptionSummary> children = new ArrayList<>();

        for ( ModelProblem problem : result.getProblems() )
        {
            ExceptionSummary child = handle( problem, result.getProjectId() );
            if ( child != null )
            {
                children.add( child );
            }
        }

        if ( children.isEmpty() )
        {
            return null;
        }

        String message =
            "\nThe project " + result.getProjectId() + " (" + result.getPomFile() + ") has "
                + children.size() + " error" + ( children.size() == 1 ? "" : "s" );

        return new ExceptionSummary( null, message, null, children );
    }
