    public XmlConfiguration(InputStream configuration) throws SAXException, IOException
    {
        InputSource source = new InputSource(configuration);
        synchronized (__parser)
        {
            _url=null;
            setConfig(__parser.parse(source));
            _dtd=__parser.getDTD();
        }
    }
