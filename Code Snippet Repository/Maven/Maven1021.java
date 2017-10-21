    private void shallowMerge( List<ToolchainModel> dominant, List<ToolchainModel> recessive,
                               String recessiveSourceLevel )
    {
        Map<Object, ToolchainModel> merged = new LinkedHashMap<>();

        for ( ToolchainModel dominantModel : dominant )
        {
            Object key = getToolchainModelKey( dominantModel );
            
            merged.put( key, dominantModel );
        }

        for ( ToolchainModel recessiveModel : recessive )
        {
            Object key = getToolchainModelKey( recessiveModel );
            
            ToolchainModel dominantModel = merged.get( key );
            if ( dominantModel == null )
            {
                recessiveModel.setSourceLevel( recessiveSourceLevel );
                dominant.add( recessiveModel );
            }
            else
            {
                mergeToolchainModel_Configuration( dominantModel, recessiveModel );
            }
        }
    }
