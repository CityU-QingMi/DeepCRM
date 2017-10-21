        public SAX2DOMBuilder() throws ParserConfigurationException {

            DocumentBuilderFactory documentBuilderFactory;
            DocumentBuilder        documentBuilder;

            documentBuilderFactory = DocumentBuilderFactory.newInstance();

            documentBuilderFactory.setValidating(false);
            documentBuilderFactory.setNamespaceAware(true);

            documentBuilder  = documentBuilderFactory.newDocumentBuilder();
            this.document    = documentBuilder.newDocument();
            this.currentNode = this.document;
        }
