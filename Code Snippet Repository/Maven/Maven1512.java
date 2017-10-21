    private static Severity getSeverity( int validationLevel, int errorThreshold )
    {
        if ( validationLevel < errorThreshold )
        {
            return Severity.WARNING;
        }
        else
        {
            return Severity.ERROR;
        }
    }
