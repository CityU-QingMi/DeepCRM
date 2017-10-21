    public XmlConfiguration(String configuration) throws SAXException, IOException
    {
        configuration = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!DOCTYPE Configure PUBLIC \"-//Jetty//Configure//EN\" \"http://eclipse.org/jetty/configure.dtd\">"
                + configuration;
        InputSource source = new InputSource(new StringReader(configuration));
        synchronized (__parser)
        {
            _url=null;
            setConfig( __parser.parse(source));
            _dtd=__parser.getDTD();
        }
    }
