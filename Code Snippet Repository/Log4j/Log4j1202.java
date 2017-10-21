    public static void reregisterMBeansAfterReconfigure(final MBeanServer mbs) {
        if (isJmxDisabled()) {
            LOGGER.debug("JMX disabled for Log4j2. Not registering MBeans.");
            return;
        }

        // now provide instrumentation for the newly configured
        // LoggerConfigs and Appenders
        try {
            final ContextSelector selector = getContextSelector();
            if (selector == null) {
                LOGGER.debug("Could not register MBeans: no ContextSelector found.");
                return;
            }
            LOGGER.trace("Reregistering MBeans after reconfigure. Selector={}", selector);
            final List<LoggerContext> contexts = selector.getLoggerContexts();
            int i = 0;
            for (final LoggerContext ctx : contexts) {
                LOGGER.trace("Reregistering context ({}/{}): '{}' {}", ++i, contexts.size(), ctx.getName(), ctx);
                // first unregister the context and all nested loggers,
                // appenders, statusLogger, contextSelector, ringbuffers...
                unregisterLoggerContext(ctx.getName(), mbs);

                final LoggerContextAdmin mbean = new LoggerContextAdmin(ctx, executor);
                register(mbs, mbean, mbean.getObjectName());

                if (ctx instanceof AsyncLoggerContext) {
                    final RingBufferAdmin rbmbean = ((AsyncLoggerContext) ctx).createRingBufferAdmin();
                    if (rbmbean.getBufferSize() > 0) {
                    	// don't register if Disruptor not started (DefaultConfiguration: config not found)
                    	register(mbs, rbmbean, rbmbean.getObjectName());
                    }
                }

                // register the status logger and the context selector
                // repeatedly
                // for each known context: if one context is unregistered,
                // these MBeans should still be available for the other
                // contexts.
                registerStatusLogger(ctx.getName(), mbs, executor);
                registerContextSelector(ctx.getName(), selector, mbs, executor);

                registerLoggerConfigs(ctx, mbs, executor);
                registerAppenders(ctx, mbs, executor);
            }
        } catch (final Exception ex) {
            LOGGER.error("Could not register mbeans", ex);
        }
    }
