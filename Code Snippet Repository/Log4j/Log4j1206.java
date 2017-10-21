    private static void registerLoggerConfigs(final LoggerContext ctx, final MBeanServer mbs, final Executor executor)
            throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {

        final Map<String, LoggerConfig> map = ctx.getConfiguration().getLoggers();
        for (final String name : map.keySet()) {
            final LoggerConfig cfg = map.get(name);
            final LoggerConfigAdmin mbean = new LoggerConfigAdmin(ctx, cfg);
            register(mbs, mbean, mbean.getObjectName());

            if (cfg instanceof AsyncLoggerConfig) {
                final AsyncLoggerConfig async = (AsyncLoggerConfig) cfg;
                final RingBufferAdmin rbmbean = async.createRingBufferAdmin(ctx.getName());
                register(mbs, rbmbean, rbmbean.getObjectName());
            }
        }
    }
