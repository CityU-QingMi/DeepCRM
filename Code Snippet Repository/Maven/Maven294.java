    private static Map<String, Expression> parseExpressionDocumentation( InputStream docStream )
        throws IOException, XmlPullParserException
    {
        Reader reader = new BufferedReader( ReaderFactory.newXmlReader( docStream ) );

        ParamdocXpp3Reader paramdocReader = new ParamdocXpp3Reader();

        ExpressionDocumentation documentation = paramdocReader.read( reader, true );

        List<Expression> expressions = documentation.getExpressions();

        Map<String, Expression> bySyntax = new HashMap<>();

        if ( expressions != null && !expressions.isEmpty() )
        {
            for ( Expression expression : expressions )
            {
                bySyntax.put( expression.getSyntax(), expression );
            }
        }

        return bySyntax;
    }
