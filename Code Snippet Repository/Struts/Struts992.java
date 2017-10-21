    public Node proxyNode(AdapterNode parent, Node node) {
        // If the property is a Document, "unwrap" it to the root element
        if (node instanceof Document) {
            node = ((Document) node).getDocumentElement();
        }

        if (node == null) {
            return null;
        }
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return new ProxyElementAdapter(this, parent, (Element) node);
        }
        if (node.getNodeType() == Node.TEXT_NODE) {
            return new ProxyTextNodeAdapter(this, parent, (Text) node);
        }
        if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
            return new ProxyAttrAdapter(this, parent, (Attr) node);
        }

        return null; // Unsupported Node type - ignore for now
    }
