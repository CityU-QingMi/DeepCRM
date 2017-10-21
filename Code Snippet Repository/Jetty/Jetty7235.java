    public synchronized Node parse(InputStream in) throws IOException, SAXException
    {
        _dtd=null;
        Handler handler = new Handler();
        XMLReader reader = _parser.getXMLReader();
        reader.setContentHandler(handler);
        reader.setErrorHandler(handler);
        reader.setEntityResolver(handler);
        _parser.parse(new InputSource(in), handler);
        if (handler._error != null)
            throw handler._error;
        Node doc = (Node) handler._top.get(0);
        handler.clear();
        return doc;
    }
