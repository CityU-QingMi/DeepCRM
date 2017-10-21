    private static void addViolation( ModelProblemCollector problems, Severity severity, Version version,
                                      String fieldName, String sourceHint, String message,
                                      InputLocationTracker tracker )
    {
        StringBuilder buffer = new StringBuilder( 256 );
        buffer.append( '\'' ).append( fieldName ).append( '\'' );

        if ( sourceHint != null )
        {
            buffer.append( " for " ).append( sourceHint );
        }

        buffer.append( ' ' ).append( message );

        // CHECKSTYLE_OFF: LineLength
        problems.add( new ModelProblemCollectorRequest( severity, version ).setMessage(
                                                                                        buffer.toString() ).setLocation( getLocation( fieldName, tracker ) ) );
        // CHECKSTYLE_ON: LineLength
    }
