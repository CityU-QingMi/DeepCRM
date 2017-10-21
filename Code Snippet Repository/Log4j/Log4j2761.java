    private void onMBeanRegistered(final ObjectName mbeanName) {
        if (client.isLoggerContext(mbeanName)) {
            try {
                final LoggerContextAdminMBean ctx = client.getLoggerContextAdmin(mbeanName);
                addWidgetForLoggerContext(ctx);
            } catch (final Exception ex) {
                handle("Could not add tab for new MBean " + mbeanName, ex);
            }
        }
    }
