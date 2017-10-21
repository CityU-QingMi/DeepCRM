    private List<CoreExtension> readCoreExtensionsDescriptor( File extensionsFile )
        throws IOException, XmlPullParserException
    {
        CoreExtensionsXpp3Reader parser = new CoreExtensionsXpp3Reader();

        try ( InputStream is = new BufferedInputStream( new FileInputStream( extensionsFile ) ) )
        {

            return parser.read( is ).getExtensions();
        }

    }
