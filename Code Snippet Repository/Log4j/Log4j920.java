    protected void preConfigure(final Node node) {
        try {
            for (final Node child : node.getChildren()) {
                if (child.getType() == null) {
                    LOGGER.error("Unable to locate plugin type for " + child.getName());
                    continue;
                }
                final Class<?> clazz = child.getType().getPluginClass();
                if (clazz.isAnnotationPresent(Scheduled.class)) {
                    configurationScheduler.incrementScheduledItems();
                }
                preConfigure(child);
            }
        } catch (final Exception ex) {
            LOGGER.error("Error capturing node data for node " + node.getName(), ex);
        }
    }
