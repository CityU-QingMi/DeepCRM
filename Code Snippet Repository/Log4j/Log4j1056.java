    static DocumentBuilder newDocumentBuilder(final boolean xIncludeAware) throws ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        disableDtdProcessing(factory);

        if (xIncludeAware) {
            enableXInclude(factory);
        }
        return factory.newDocumentBuilder();
    }
