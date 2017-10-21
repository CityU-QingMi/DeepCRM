    public DefaultModelProblemCollector( ModelBuildingResult result )
    {
        this.result = result;
        this.problems = result.getProblems();

        for ( ModelProblem problem : this.problems )
        {
            severities.add( problem.getSeverity() );
        }
    }
