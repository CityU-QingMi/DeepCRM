    @Override
    public Object execute( String expression, Object value )
    {
        if ( value != null )
        {
            expression = ValueSourceUtils.trimPrefix( expression, expressionPrefixes, true );

            if ( unprefixedPathKeys.contains( expression ) )
            {
                return pathTranslator.alignToBaseDirectory( String.valueOf( value ), projectDir );
            }
        }

        return null;
    }
