    public NamedAttribute getNamedAttributeNode(String name) {
        NamedAttribute result = null;

        // Look for the attribute in NamedAttribute children
        Nodes nodes = getNamedAttributeNodes();
        int numChildNodes = nodes.size();
        for (int i = 0; i < numChildNodes; i++) {
            NamedAttribute na = (NamedAttribute) nodes.getNode(i);
            boolean found = false;
            int index = name.indexOf(':');
            if (index != -1) {
                // qualified name
                found = na.getName().equals(name);
            } else {
                found = na.getLocalName().equals(name);
            }
            if (found) {
                result = na;
                break;
            }
        }

        return result;
    }
