    private static SAXParser getSAXParser(
        boolean validating,
        JspDocumentParser jspDocParser)
        throws Exception {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);

        // Preserve xmlns attributes
        factory.setFeature(
            "http://xml.org/sax/features/namespace-prefixes",
            true);
        factory.setValidating(validating);
        //factory.setFeature(
        //    "http://xml.org/sax/features/validation",
        //    validating);
        
        // Configure the parser
        SAXParser saxParser = factory.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setProperty(LEXICAL_HANDLER_PROPERTY, jspDocParser);
        xmlReader.setErrorHandler(jspDocParser);

        return saxParser;
    }
