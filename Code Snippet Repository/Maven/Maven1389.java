    @Deprecated
    public ModelBuildingException( Model model, String modelId, List<ModelProblem> problems )
    {
        super( toMessage( modelId, problems ) );

        if ( model != null )
        {
            DefaultModelBuildingResult tmp = new DefaultModelBuildingResult();
            if ( modelId == null )
            {
                modelId = "";
            }
            tmp.addModelId( modelId );
            tmp.setRawModel( modelId, model );
            tmp.setProblems( problems );
            result = tmp;
        }
        else
        {
            result = null;
        }
    }
