    public static void unregisterMBeans(final MBeanServer mbs) {
        unregisterStatusLogger("*", mbs);
        unregisterContextSelector("*", mbs);
        unregisterContexts(mbs);
        unregisterLoggerConfigs("*", mbs);
        unregisterAsyncLoggerRingBufferAdmins("*", mbs);
        unregisterAsyncLoggerConfigRingBufferAdmins("*", mbs);
        unregisterAppenders("*", mbs);
        unregisterAsyncAppenders("*", mbs);
    }
