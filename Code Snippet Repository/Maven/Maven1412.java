    @Override
    public Object getValue( String expression )
    {
        Object value = valueSource.getValue( expression );

        if ( value != null && expression.startsWith( bannedPrefix ) )
        {
            String msg = "The expression ${" + expression + "} is deprecated.";
            if ( newPrefix != null && newPrefix.length() > 0 )
            {
                msg += " Please use ${" + newPrefix + expression.substring( bannedPrefix.length() ) + "} instead.";
            }
            problems.add( new ModelProblemCollectorRequest( Severity.WARNING, Version.V20 ).setMessage( msg ) );
        }

        return value;
    }
