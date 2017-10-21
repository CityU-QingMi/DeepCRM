    public static void unregisterLoggerContext(final String contextName, final MBeanServer mbs) {
        final String search = String.format(LoggerContextAdminMBean.PATTERN, escape(contextName), "*");
        unregisterAllMatching(search, mbs); // unregister context mbean

        // now unregister all MBeans associated with this logger context
        unregisterStatusLogger(contextName, mbs);
        unregisterContextSelector(contextName, mbs);
        unregisterLoggerConfigs(contextName, mbs);
        unregisterAppenders(contextName, mbs);
        unregisterAsyncAppenders(contextName, mbs);
        unregisterAsyncLoggerRingBufferAdmins(contextName, mbs);
        unregisterAsyncLoggerConfigRingBufferAdmins(contextName, mbs);
    }
