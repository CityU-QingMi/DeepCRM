    public static Map<String, Expression> load()
        throws ExpressionDocumentationException
    {
        if ( expressionDocumentation == null )
        {
            expressionDocumentation = new HashMap<>();

            ClassLoader docLoader = initializeDocLoader();

            for ( String root : EXPRESSION_ROOTS )
            {
                try ( InputStream docStream = docLoader.getResourceAsStream(
                    EXPRESSION_DOCO_ROOTPATH + root + ".paramdoc.xml" ) )
                {
                    if ( docStream != null )
                    {
                        Map<String, Expression> doco = parseExpressionDocumentation( docStream );

                        expressionDocumentation.putAll( doco );
                    }
                }
                catch ( IOException e )
                {
                    throw new ExpressionDocumentationException(
                        "Failed to read documentation for expression root: " + root, e );
                }
                catch ( XmlPullParserException e )
                {
                    throw new ExpressionDocumentationException(
                        "Failed to parse documentation for expression root: " + root, e );
                }

            }
        }

        return expressionDocumentation;
    }
