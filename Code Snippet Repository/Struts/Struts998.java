    protected List<Node> buildChildAdapters() {
        List<Node> adapters = new ArrayList<>();
        NodeList children = node().getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            Node adapter = wrap(child);
            if (adapter != null) {
                log.debug("Wrapped child node: {}", child.getNodeName());
                adapters.add(adapter);
            }
        }
        return adapters;
    }
