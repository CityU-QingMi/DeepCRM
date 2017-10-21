    private Node getLoggerNode(final Node parentNode, final String name) {
        for (final Node node : parentNode.getChildren()) {
            final String nodeName = node.getAttributes().get(NAME);
            if (name == null && nodeName == null) {
                return node;
            }
            if (nodeName != null && nodeName.equals(name)) {
                return node;
            }
        }
        return null;
    }
