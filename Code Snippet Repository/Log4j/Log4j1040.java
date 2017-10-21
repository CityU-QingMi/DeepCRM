    @Override
    public Object visit(final Configuration configuration, final Node node, final LogEvent event,
                        final StringBuilder log) {
        if (this.conversionType.isInstance(node)) {
            log.append("Node=").append(node.getName());
            return node;
        }
        LOGGER.warn("Variable annotated with @PluginNode is not compatible with the type {}.", node.getClass());
        return null;
    }
