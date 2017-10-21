    @Override
    public XmlParser ensureParser() throws ClassNotFoundException
    {
        synchronized (WebDescriptor.class)
        {
            if (_nonValidatingStaticParser == null)
                _nonValidatingStaticParser = newParser(false);
        }
        
        if (!isValidating())
            return _nonValidatingStaticParser;
        else
            return newParser(true);
    }
