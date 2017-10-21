    public Node.Nodes getNamedAttributeNodes() {

        if (namedAttributeNodes != null) {
            return namedAttributeNodes;
        }

        Node.Nodes result = new Node.Nodes();

        // Look for the attribute in NamedAttribute children
        Nodes nodes = getBody();
        if (nodes != null) {
            int numChildNodes = nodes.size();
            for (int i = 0; i < numChildNodes; i++) {
                Node n = nodes.getNode(i);
                if (n instanceof NamedAttribute) {
                    result.add(n);
                } else if (!(n instanceof Comment)) {
                    // Nothing can come before jsp:attribute, and only
                    // jsp:body can come after it.
                    break;
                }
            }
        }

        namedAttributeNodes = result;
        return result;
    }
