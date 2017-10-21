    private static void workAroundLog4j2_5Bug() {
        // use reflection so we can use the same test with older versions of log4j2
        try {
            final Method setUseThreadLocals =
                    AsyncLoggerContext.class.getDeclaredMethod("setUseThreadLocals", new Class[]{boolean.class});
            final LoggerContext context = LogManager.getContext(false);
            setUseThreadLocals.invoke(context, new Object[] {Boolean.TRUE});
        } catch (final Throwable ignored) {
        }
    }
