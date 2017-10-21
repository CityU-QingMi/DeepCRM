    public ObjectName getStatusLoggerObjectName(final ObjectName loggerContextObjName) {
        if (!isLoggerContext(loggerContextObjName)) {
            throw new IllegalArgumentException("Not a LoggerContext: " + loggerContextObjName);
        }
        final String cxtName = loggerContextObjName.getKeyProperty("type");
        final String name = String.format(StatusLoggerAdminMBean.PATTERN, cxtName);
        try {
            return new ObjectName(name);
        } catch (final MalformedObjectNameException ex) {
            throw new IllegalStateException(name, ex);
        }
    }
