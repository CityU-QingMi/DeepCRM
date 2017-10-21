    private ModelProblem hasMissingParentPom( ProjectBuildingException e )
    {
        if ( e.getCause() instanceof ModelBuildingException )
        {
            ModelBuildingException mbe = (ModelBuildingException) e.getCause();
            for ( ModelProblem problem : mbe.getProblems() )
            {
                if ( problem.getException() instanceof UnresolvableModelException )
                {
                    return problem;
                }
            }

        }
        return null;
    }
