    @Override
    public void add( SettingsProblem.Severity severity, String message, int line, int column, Exception cause )
    {
        if ( line <= 0 && column <= 0 && ( cause instanceof SettingsParseException ) )
        {
            SettingsParseException e = (SettingsParseException) cause;
            line = e.getLineNumber();
            column = e.getColumnNumber();
        }

        SettingsProblem problem = new DefaultSettingsProblem( message, severity, source, line, column, cause );

        problems.add( problem );
    }
