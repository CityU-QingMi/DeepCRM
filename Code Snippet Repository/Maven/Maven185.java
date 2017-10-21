    public ModelValidationResult validate( Model model )
    {
        ModelValidationResult result = new ModelValidationResult();

        ModelBuildingRequest request =
            new DefaultModelBuildingRequest().setValidationLevel( ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_2_0 );

        SimpleModelProblemCollector problems = new SimpleModelProblemCollector( result );

        modelValidator.validateEffectiveModel( model, request, problems );

        return result;
    }
