    private void updateFilterNode(final Node target, final Node targetChildNode, final Node sourceChildNode,
            final PluginManager pluginManager) {
        if (CompositeFilter.class.isAssignableFrom(targetChildNode.getType().getPluginClass())) {
            final Node node = new Node(targetChildNode, sourceChildNode.getName(), sourceChildNode.getType());
            node.getChildren().addAll(sourceChildNode.getChildren());
            node.getAttributes().putAll(sourceChildNode.getAttributes());
            targetChildNode.getChildren().add(node);
        } else {
            final PluginType pluginType = pluginManager.getPluginType(FILTERS);
            final Node filtersNode = new Node(targetChildNode, FILTERS, pluginType);
            final Node node = new Node(filtersNode, sourceChildNode.getName(), sourceChildNode.getType());
            node.getAttributes().putAll(sourceChildNode.getAttributes());
            final List<Node> children = filtersNode.getChildren();
            children.add(targetChildNode);
            children.add(node);
            final List<Node> nodes = target.getChildren();
            nodes.remove(targetChildNode);
            nodes.add(filtersNode);
        }
    }
