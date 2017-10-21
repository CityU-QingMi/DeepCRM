    private void scanXMLDeclOrTextDecl(boolean scanningTextDecl)
            throws IOException, JasperException {

        // scan decl
        scanXMLDeclOrTextDecl(scanningTextDecl, fStrings);
        fMarkupDepth--;

        // pseudo-attribute values
        String encodingPseudoAttr = fStrings[1];

        // set encoding on reader
        if (encodingPseudoAttr != null) {
            isEncodingSetInProlog = true;
            encoding = encodingPseudoAttr;
        }
    }
