    private boolean validateVersion( String fieldName, ModelProblemCollector problems, Severity severity,
                                     Version version, String string, String sourceHint, InputLocationTracker tracker )
    {
        if ( string == null || string.length() <= 0 )
        {
            return true;
        }

        if ( hasExpression( string ) )
        {
            addViolation( problems, severity, version, fieldName, sourceHint,
                          "must be a valid version but is '" + string + "'.", tracker );
            return false;
        }

        return validateBannedCharacters( fieldName, problems, severity, version, string, sourceHint, tracker,
                                         ILLEGAL_VERSION_CHARS );

    }
