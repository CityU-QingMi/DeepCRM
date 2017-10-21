    public Object execute( String expression,
                                      Object value )
    {
        expression = ValueSourceUtils.trimPrefix( expression, expressionPrefixes, true );

        if ( projectDir != null && value != null && unprefixedPathKeys.contains( expression ) )
        {
            return pathTranslator.alignToBaseDirectory( String.valueOf( value ), projectDir );
        }

        return value;
    }
