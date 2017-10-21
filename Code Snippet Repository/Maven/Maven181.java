    private String stripBasedirToken( String s )
    {
        if ( s != null )
        {
            String basedirExpr = null;
            for ( String expression : BASEDIR_EXPRESSIONS )
            {
                if ( s.startsWith( expression ) )
                {
                    basedirExpr = expression;
                    break;
                }
            }

            if ( basedirExpr != null )
            {
                if ( s.length() > basedirExpr.length() )
                {
                    // Take out basedir expression and the leading slash
                    s = chopLeadingFileSeparator( s.substring( basedirExpr.length() ) );
                }
                else
                {
                    s = ".";
                }
            }
        }

        return s;
    }
