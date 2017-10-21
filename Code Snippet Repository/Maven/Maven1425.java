    @Override
    public Object execute( String expression, Object value )
    {
        if ( value != null && URL_EXPRESSIONS.contains( expression ) )
        {
            return normalizer.normalize( value.toString() );
        }

        return null;
    }
