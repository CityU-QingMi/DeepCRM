    public static Document parse(InputSource inputSource, Map<String, String> dtdMappings) {
                
        SAXParserFactory factory = null;
        String parserProp = System.getProperty("xwork.saxParserFactory");
        if (parserProp != null) {
            try {
                ObjectFactory objectFactory = ActionContext.getContext().getContainer().getInstance(ObjectFactory.class);
                Class clazz = objectFactory.getClassInstance(parserProp);
                factory = (SAXParserFactory) clazz.newInstance();
            } catch (Exception e) {
                LOG.error("Unable to load saxParserFactory set by system property 'xwork.saxParserFactory': {}", parserProp, e);
            }
        }

        if (factory == null) {
            factory = SAXParserFactory.newInstance();
        }

        factory.setValidating((dtdMappings != null));
        factory.setNamespaceAware(true);

        SAXParser parser;
        try {
            parser = factory.newSAXParser();
        } catch (Exception ex) {
            throw new XWorkException("Unable to create SAX parser", ex);
        }
        
        
        DOMBuilder builder = new DOMBuilder();

        // Enhance the sax stream with location information
        ContentHandler locationHandler = new LocationAttributes.Pipe(builder);
        
        try {
            parser.parse(inputSource, new StartHandler(locationHandler, dtdMappings));
        } catch (Exception ex) {
            throw new XWorkException(ex);
        }
        
        return builder.getDocument();
    }
