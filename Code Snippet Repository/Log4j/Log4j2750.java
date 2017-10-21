    public StatusLoggerAdminMBean getStatusLoggerAdmin(final String contextName)
            throws MalformedObjectNameException, IOException {
        final String pattern = StatusLoggerAdminMBean.PATTERN;
        final String mbean = String.format(pattern, Server.escape(contextName));
        final ObjectName search = new ObjectName(mbean);
        final Set<ObjectName> result = connection.queryNames(search, null);
        if (result.size() == 0) {
            return null;
        }
        if (result.size() > 1) {
            System.err.println("WARN: multiple status loggers found for " + contextName + ": " + result);
        }
        final StatusLoggerAdminMBean proxy = JMX.newMBeanProxy(connection, //
                result.iterator().next(), //
                StatusLoggerAdminMBean.class, true); // notificationBroadcaster
        return proxy;
    }
