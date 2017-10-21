    private boolean validateBannedCharacters( String fieldName, ModelProblemCollector problems, Severity severity,
                                              Version version, String string, String sourceHint,
                                              InputLocationTracker tracker, String banned )
    {
        if ( string != null )
        {
            for ( int i = string.length() - 1; i >= 0; i-- )
            {
                if ( banned.indexOf( string.charAt( i ) ) >= 0 )
                {
                    addViolation( problems, severity, version, fieldName, sourceHint,
                                  "must not contain any of these characters " + banned + " but found "
                                      + string.charAt( i ),
                                  tracker );
                    return false;
                }
            }
        }

        return true;
    }
