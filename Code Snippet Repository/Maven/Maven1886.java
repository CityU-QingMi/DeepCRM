    private static boolean validateNotNull( SettingsProblemCollector problems, String fieldName, Object object,
                                            String sourceHint )
    {
        if ( object != null )
        {
            return true;
        }

        addViolation( problems, Severity.ERROR, fieldName, sourceHint, "is missing" );

        return false;
    }
