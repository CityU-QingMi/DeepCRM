    private boolean validate20ProperSnapshotVersion( String fieldName, ModelProblemCollector problems,
                                                     Severity severity, Version version, String string,
                                                     String sourceHint, InputLocationTracker tracker )
    {
        if ( string == null || string.length() <= 0 )
        {
            return true;
        }

        if ( string.endsWith( "SNAPSHOT" ) && !string.endsWith( "-SNAPSHOT" ) )
        {
            addViolation( problems, severity, version, fieldName, sourceHint,
                          "uses an unsupported snapshot version format, should be '*-SNAPSHOT' instead.", tracker );
            return false;
        }

        return true;
    }
