    private boolean validateEnum( String fieldName, ModelProblemCollector problems, Severity severity, Version version,
                                  String string, String sourceHint, InputLocationTracker tracker,
                                  String... validValues )
    {
        if ( string == null || string.length() <= 0 )
        {
            return true;
        }

        List<String> values = Arrays.asList( validValues );

        if ( values.contains( string ) )
        {
            return true;
        }

        addViolation( problems, severity, version, fieldName, sourceHint,
                      "must be one of " + values + " but is '" + string + "'.", tracker );

        return false;
    }
