    public DefaultModelBuildingResult setProblems( List<ModelProblem> problems )
    {
        if ( problems != null )
        {
            this.problems = new ArrayList<>( problems );
        }
        else
        {
            this.problems.clear();
        }

        return this;
    }
