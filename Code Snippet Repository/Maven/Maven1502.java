    private boolean validateNotNull( String fieldName, ModelProblemCollector problems, Severity severity,
                                     Version version, Object object, String sourceHint, InputLocationTracker tracker )
    {
        if ( object != null )
        {
            return true;
        }

        addViolation( problems, severity, version, fieldName, sourceHint, "is missing.", tracker );

        return false;
    }
