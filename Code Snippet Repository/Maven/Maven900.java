    public ExtensionDescriptor build( InputStream is )
        throws IOException
    {
        ExtensionDescriptor extensionDescriptor = new ExtensionDescriptor();

        Xpp3Dom dom;
        try
        {
            dom = Xpp3DomBuilder.build( ReaderFactory.newXmlReader( is ) );
        }
        catch ( XmlPullParserException e )
        {
            throw (IOException) new IOException( e.getMessage() ).initCause( e );
        }

        if ( !"extension".equals( dom.getName() ) )
        {
            throw new IOException( "Unexpected root element \"" + dom.getName() + "\", expected \"extension\"" );
        }

        extensionDescriptor.setExportedPackages( parseStrings( dom.getChild( "exportedPackages" ) ) );

        extensionDescriptor.setExportedArtifacts( parseStrings( dom.getChild( "exportedArtifacts" ) ) );

        return extensionDescriptor;
    }
