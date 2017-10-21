    private String findProfilesXml( ModelBuildingResult result, Map<File, Boolean> profilesXmls )
    {
        for ( String modelId : result.getModelIds() )
        {
            Model model = result.getRawModel( modelId );

            File basedir = model.getProjectDirectory();
            if ( basedir == null )
            {
                break;
            }

            Boolean profilesXml = profilesXmls.get( basedir );
            if ( profilesXml == null )
            {
                profilesXml = new File( basedir, "profiles.xml" ).exists();
                profilesXmls.put( basedir, profilesXml );
            }
            if ( profilesXml )
            {
                return modelId;
            }
        }

        return null;
    }
