    private void addWidgetForLoggerContext(final LoggerContextAdminMBean ctx) throws MalformedObjectNameException,
            IOException, InstanceNotFoundException {
        final JTabbedPane contextTabs = new JTabbedPane();
        contextObjNameToTabbedPaneMap.put(ctx.getObjectName(), contextTabs);
        tabbedPaneContexts.addTab("LoggerContext: " + ctx.getName(), contextTabs);

        final String contextName = ctx.getName();
        final StatusLoggerAdminMBean status = client.getStatusLoggerAdmin(contextName);
        if (status != null) {
            final JTextArea text = createTextArea();
            final String[] messages = status.getStatusDataHistory();
            for (final String message : messages) {
                text.append(message + '\n');
            }
            statusLogTextAreaMap.put(ctx.getObjectName(), text);
            registerListeners(status);
            final JScrollPane scroll = scroll(text);
            contextTabs.addTab("StatusLogger", scroll);
        }

        final ClientEditConfigPanel editor = new ClientEditConfigPanel(ctx);
        contextTabs.addTab("Configuration", editor);
    }
