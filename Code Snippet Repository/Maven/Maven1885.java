    private static boolean validateStringNotEmpty( SettingsProblemCollector problems, String fieldName, String string,
                                            String sourceHint )
    {
        if ( !validateNotNull( problems, fieldName, string, sourceHint ) )
        {
            return false;
        }

        if ( string.length() > 0 )
        {
            return true;
        }

        addViolation( problems, Severity.ERROR, fieldName, sourceHint, "is missing" );

        return false;
    }
