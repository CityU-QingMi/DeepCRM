    private static String inheritedGroupId( final ModelBuildingResult result, final int modelIndex )
    {
        String groupId = null;
        final String modelId = result.getModelIds().get( modelIndex );

        if ( !modelId.isEmpty() )
        {
            final Model model = result.getRawModel( modelId );
            groupId = model.getGroupId() != null
                          ? model.getGroupId()
                          : inheritedGroupId( result, modelIndex + 1 );

        }

        return groupId;
    }
