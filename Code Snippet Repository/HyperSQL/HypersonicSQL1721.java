    protected static Document createDocument(String namespaceURI,
            String qualifiedName, DocumentType docType) throws SQLException {

        try {
            return getDOMImplementation().createDocument(namespaceURI,
                    qualifiedName, docType);
        } catch (DOMException ex) {
            throw Exceptions.domInstantiation(ex);
        }
    }
