    private static long initStartTime() {
        // LOG4J2-379:
        // We'd like to call ManagementFactory.getRuntimeMXBean().getStartTime(),
        // but Google App Engine throws a java.lang.NoClassDefFoundError
        // "java.lang.management.ManagementFactory is a restricted class".
        // The reflection is necessary because without it, Google App Engine
        // will refuse to initialize this class.
        try {
            final Class<?> factoryClass = Loader.loadSystemClass("java.lang.management.ManagementFactory");
            final Method getRuntimeMXBean = factoryClass.getMethod("getRuntimeMXBean");
            final Object runtimeMXBean = getRuntimeMXBean.invoke(null);

            final Class<?> runtimeMXBeanClass = Loader.loadSystemClass("java.lang.management.RuntimeMXBean");
            final Method getStartTime = runtimeMXBeanClass.getMethod("getStartTime");
            final Long result = (Long) getStartTime.invoke(runtimeMXBean);

            return result;
        } catch (final Throwable t) {
            StatusLogger.getLogger().error("Unable to call ManagementFactory.getRuntimeMXBean().getStartTime(), "
                    + "using system time for OnStartupTriggeringPolicy", t);
            // We have little option but to declare "now" as the beginning of time.
            return System.currentTimeMillis();
        }
    }
