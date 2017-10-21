    private boolean validateStringNoExpression( String fieldName, ModelProblemCollector problems, Severity severity,
                                                Version version, String string, InputLocationTracker tracker )
    {
        if ( !hasExpression( string ) )
        {
            return true;
        }

        addViolation( problems, severity, version, fieldName, null, "contains an expression but should be a constant.",
                      tracker );

        return false;
    }
