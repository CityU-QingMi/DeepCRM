        @Override
        public AppenderSet build() {
            if (configuration == null) {
                LOGGER.error("Configuration is missing from AppenderSet {}", this);
                return null;                
            }
            if (node == null) {
                LOGGER.error("No node in AppenderSet {}", this);
                return null;
            }
            final List<Node> children = node.getChildren();
            if (children == null) {
                LOGGER.error("No children node in AppenderSet {}", this);
                return null;
            }
            final Map<String, Node> map = new HashMap<>(children.size());
            for (final Node childNode : children) {
                final String key = childNode.getAttributes().get("name");
                if (key == null) {
                    LOGGER.error("The attribute 'name' is missing from from the node {} in AppenderSet {}",
                            childNode, children);
                } else {
                    map.put(key, childNode);
                }
            }
            return new AppenderSet(configuration, map);
        }
