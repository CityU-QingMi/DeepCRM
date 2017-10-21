    public synchronized Node parse(InputSource source) throws IOException, SAXException
    {
        _dtd=null;
        Handler handler = new Handler();
        XMLReader reader = _parser.getXMLReader();
        reader.setContentHandler(handler);
        reader.setErrorHandler(handler);
        reader.setEntityResolver(handler);
        if (LOG.isDebugEnabled())
            LOG.debug("parsing: sid=" + source.getSystemId() + ",pid=" + source.getPublicId());
        _parser.parse(source, handler);
        if (handler._error != null)
            throw handler._error;
        Node doc = (Node) handler._top.get(0);
        handler.clear();
        return doc;
    }
