    private static void addViolation( SettingsProblemCollector problems, Severity severity, String fieldName,
                               String sourceHint, String message )
    {
        StringBuilder buffer = new StringBuilder( 256 );
        buffer.append( '\'' ).append( fieldName ).append( '\'' );

        if ( sourceHint != null )
        {
            buffer.append( " for " ).append( sourceHint );
        }

        buffer.append( ' ' ).append( message );

        problems.add( severity, buffer.toString(), -1, -1, null );
    }
