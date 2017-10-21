    private Element getRootElement() {
        if (rootElement != null)
            return rootElement;

        Node node = getAdapterFactory().adaptNode(
                this, getPropertyName(), getPropertyValue());
        if (node instanceof Element)
            rootElement = (Element) node;
        else
            throw new StrutsException(
                    "Document adapter expected to wrap an Element type.  Node is not an element:" + node);

        return rootElement;
    }
