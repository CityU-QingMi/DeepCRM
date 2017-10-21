    private static ClassLoader initializeDocLoader()
        throws ExpressionDocumentationException
    {
        String myResourcePath = ExpressionDocumenter.class.getName().replace( '.', '/' ) + ".class";

        URL myResource = ExpressionDocumenter.class.getClassLoader().getResource( myResourcePath );

        assert myResource != null : "The resource is this class itself loaded by its own classloader and must exist";

        String myClasspathEntry = myResource.getPath();

        myClasspathEntry = myClasspathEntry.substring( 0, myClasspathEntry.length() - ( myResourcePath.length() + 2 ) );

        if ( myClasspathEntry.startsWith( "file:" ) )
        {
            myClasspathEntry = myClasspathEntry.substring( "file:".length() );
        }

        URL docResource;
        try
        {
            docResource = new File( myClasspathEntry ).toURL();
        }
        catch ( MalformedURLException e )
        {
            throw new ExpressionDocumentationException(
                "Cannot construct expression documentation classpath" + " resource base.", e );
        }

        return new URLClassLoader( new URL[]
        {
            docResource
        } );
    }
