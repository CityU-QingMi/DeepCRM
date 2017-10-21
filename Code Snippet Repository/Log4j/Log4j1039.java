    private Node findNamedNode(final String name, final Iterable<Node> children) {
        for (final Node child : children) {
            final PluginType<?> childType = child.getType();
            if (childType == null) {
                //System.out.println();
            }
            if (name.equalsIgnoreCase(childType.getElementName()) ||
                this.conversionType.isAssignableFrom(childType.getPluginClass())) {
                // FIXME: check child.getObject() for null?
                // doing so would be more consistent with the array version
                return child;
            }
        }
        return null;
    }
