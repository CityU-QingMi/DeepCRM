    private SimpleProblemCollector validateRaw( String pom, int level )
        throws Exception
    {
        ModelBuildingRequest request = new DefaultModelBuildingRequest().setValidationLevel( level );

        SimpleProblemCollector problems = new SimpleProblemCollector( read( pom ) );

        validator.validateRawModel( problems.getModel(), request, problems );

        return problems;
    }
