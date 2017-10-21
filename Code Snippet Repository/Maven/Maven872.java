    private static String inheritedVersion( final ModelBuildingResult result, final int modelIndex )
    {
        String version = null;
        final String modelId = result.getModelIds().get( modelIndex );

        if ( !modelId.isEmpty() )
        {
            final Model model = result.getRawModel( modelId );
            version = model.getVersion() != null
                          ? model.getVersion()
                          : inheritedVersion( result, modelIndex + 1 );

        }

        return version;
    }
