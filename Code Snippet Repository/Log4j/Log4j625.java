    public Appender createAppender(final String appenderName, final String actualName) {
        final Node node = nodeMap.get(appenderName);
        if (node == null) {
            LOGGER.error("No node named {} in {}", appenderName, this);
            return null;
        }
        node.getAttributes().put("name", actualName);
        if (node.getType().getElementName().equals(Appender.ELEMENT_TYPE)) {
            final Node appNode = new Node(node);
            configuration.createConfiguration(appNode, null);
            if (appNode.getObject() instanceof Appender) {
                final Appender app = appNode.getObject();
                app.start();
                return app;
            }
            LOGGER.error("Unable to create Appender of type " + node.getName());
            return null;
        }
        LOGGER.error("No Appender was configured for name {} " + appenderName);
        return null;
    }
