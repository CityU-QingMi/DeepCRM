    private boolean validateVersionNoExpression( String fieldName, ModelProblemCollector problems, Severity severity,
                                                 Version version, String string, InputLocationTracker tracker )
    {

        if ( !hasExpression( string ) )
        {
            return true;
        }

        //
        // Acceptable versions for continuous delivery
        //
        // changelist
        // revision
        // sha1
        //
        string = string.trim();
        if ( string.contains( "${" + AbstractStringBasedModelInterpolator.CHANGELIST_PROPERTY + "}" )
            || string.contains( "${" + AbstractStringBasedModelInterpolator.REVISION_PROPERTY + "}" )
            || string.contains( "${" + AbstractStringBasedModelInterpolator.SHA1_PROPERTY + "}" ) )
        {
            return true;
        }

        addViolation( problems, severity, version, fieldName, null, "contains an expression but should be a constant.",
                      tracker );

        return false;
    }
