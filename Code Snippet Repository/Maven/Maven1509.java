    private boolean validate20PluginVersion( String fieldName, ModelProblemCollector problems, String string,
                                             String sourceHint, InputLocationTracker tracker,
                                             ModelBuildingRequest request )
    {
        if ( string == null )
        {
            // NOTE: The check for missing plugin versions is handled directly by the model builder
            return true;
        }

        Severity errOn30 = getSeverity( request, ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_3_0 );

        if ( !validateVersion( fieldName, problems, errOn30, Version.V20, string, sourceHint, tracker ) )
        {
            return false;
        }

        if ( string.length() <= 0 || "RELEASE".equals( string ) || "LATEST".equals( string ) )
        {
            addViolation( problems, errOn30, Version.V20, fieldName, sourceHint,
                          "must be a valid version but is '" + string + "'.", tracker );
            return false;
        }

        return true;
    }
