    private SimpleProblemCollector validateEffective( String pom, int level )
        throws Exception
    {
        ModelBuildingRequest request = new DefaultModelBuildingRequest().setValidationLevel( level );

        SimpleProblemCollector problems = new SimpleProblemCollector( read( pom ) );

        validator.validateEffectiveModel( problems.getModel(), request, problems );

        return problems;
    }
