    private static boolean validateBannedCharacters( SettingsProblemCollector problems, String fieldName,
                                                     Severity severity, String string, String sourceHint,
                                                     String banned )
    {
        if ( string != null )
        {
            for ( int i = string.length() - 1; i >= 0; i-- )
            {
                if ( banned.indexOf( string.charAt( i ) ) >= 0 )
                {
                    addViolation( problems, severity, fieldName, sourceHint,
                                  "must not contain any of these characters " + banned + " but found "
                                      + string.charAt( i ) );
                    return false;
                }
            }
        }

        return true;
    }
