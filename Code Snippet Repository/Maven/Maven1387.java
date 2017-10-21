    public ModelBuildingException newModelBuildingException()
    {
        ModelBuildingResult result = this.result;
        if ( result.getModelIds().isEmpty() )
        {
            DefaultModelBuildingResult tmp = new DefaultModelBuildingResult();
            tmp.setEffectiveModel( result.getEffectiveModel() );
            tmp.setProblems( getProblems() );
            tmp.setActiveExternalProfiles( result.getActiveExternalProfiles() );
            String id = getRootModelId();
            tmp.addModelId( id );
            tmp.setRawModel( id, getRootModel() );
            result = tmp;
        }
        return new ModelBuildingException( result );
    }
