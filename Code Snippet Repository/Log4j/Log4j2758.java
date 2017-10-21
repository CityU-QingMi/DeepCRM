    private void removeWidgetForLoggerContext(final ObjectName loggerContextObjName) throws JMException, IOException {
        final Component tab = contextObjNameToTabbedPaneMap.get(loggerContextObjName);
        if (tab != null) {
            tabbedPaneContexts.remove(tab);
        }
        statusLogTextAreaMap.remove(loggerContextObjName);
        final ObjectName objName = client.getStatusLoggerObjectName(loggerContextObjName);
        try {
            // System.out.println("Remove listener for " + objName);
            client.getConnection().removeNotificationListener(objName, this);
        } catch (final ListenerNotFoundException ignored) {
        }
    }
