    private boolean validateId( String fieldName, ModelProblemCollector problems, Severity severity, Version version,
                                String id, String sourceHint, InputLocationTracker tracker )
    {
        if ( !validateStringNotEmpty( fieldName, problems, severity, version, id, sourceHint, tracker ) )
        {
            return false;
        }
        else
        {
            boolean match = ID_REGEX.matcher( id ).matches();
            if ( !match )
            {
                addViolation( problems, severity, version, fieldName, sourceHint,
                              "with value '" + id + "' does not match a valid id pattern.", tracker );
            }
            return match;
        }
    }
