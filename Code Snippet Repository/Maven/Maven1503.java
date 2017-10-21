    private boolean validateBoolean( String fieldName, ModelProblemCollector problems, Severity severity,
                                     Version version, String string, String sourceHint, InputLocationTracker tracker )
    {
        if ( string == null || string.length() <= 0 )
        {
            return true;
        }

        if ( "true".equalsIgnoreCase( string ) || "false".equalsIgnoreCase( string ) )
        {
            return true;
        }

        addViolation( problems, severity, version, fieldName, sourceHint,
                      "must be 'true' or 'false' but is '" + string + "'.", tracker );

        return false;
    }
