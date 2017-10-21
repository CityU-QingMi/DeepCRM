    protected List<Node> buildChildAdapters() {
        Node node;
        if (getParseStringAsXML()) {
            log.debug("parsing string as xml: {}", getStringValue());
            // Parse the String to a DOM, then proxy that as our child
            node = DomHelper.parse(new InputSource(new StringReader(getStringValue())));
            node = getAdapterFactory().proxyNode(this, node);
        } else {
            log.debug("using string as is: {}", getStringValue());
            // Create a Text node as our child
            node = new SimpleTextNode(getAdapterFactory(), this, "text", getStringValue());
        }

        List<Node> children = new ArrayList<>();
        children.add(node);
        return children;
    }
