    private boolean validateStringNotEmpty( String fieldName, ModelProblemCollector problems, Severity severity,
                                            Version version, String string, String sourceHint,
                                            InputLocationTracker tracker )
    {
        if ( !validateNotNull( fieldName, problems, severity, version, string, sourceHint, tracker ) )
        {
            return false;
        }

        if ( string.length() > 0 )
        {
            return true;
        }

        addViolation( problems, severity, version, fieldName, sourceHint, "is missing.", tracker );

        return false;
    }
