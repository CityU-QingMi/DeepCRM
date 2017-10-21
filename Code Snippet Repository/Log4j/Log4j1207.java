    private static void registerAppenders(final LoggerContext ctx, final MBeanServer mbs, final Executor executor)
            throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {

        final Map<String, Appender> map = ctx.getConfiguration().getAppenders();
        for (final String name : map.keySet()) {
            final Appender appender = map.get(name);

            if (appender instanceof AsyncAppender) {
                final AsyncAppender async = ((AsyncAppender) appender);
                final AsyncAppenderAdmin mbean = new AsyncAppenderAdmin(ctx.getName(), async);
                register(mbs, mbean, mbean.getObjectName());
            } else {
                final AppenderAdmin mbean = new AppenderAdmin(ctx.getName(), appender);
                register(mbs, mbean, mbean.getObjectName());
            }
        }
    }
