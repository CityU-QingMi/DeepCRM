    @Override
    public synchronized void start() {
        if (disruptor != null) {
            LOGGER.trace(
                    "[{}] AsyncLoggerDisruptor not starting new disruptor for this context, using existing object.",
                    contextName);
            return;
        }
        LOGGER.trace("[{}] AsyncLoggerDisruptor creating new disruptor for this context.", contextName);
        ringBufferSize = DisruptorUtil.calculateRingBufferSize("AsyncLogger.RingBufferSize");
        final WaitStrategy waitStrategy = DisruptorUtil.createWaitStrategy("AsyncLogger.WaitStrategy");

        final ThreadFactory threadFactory = new Log4jThreadFactory("AsyncLogger[" + contextName + "]", true, Thread.NORM_PRIORITY) {
            @Override
            public Thread newThread(final Runnable r) {
                final Thread result = super.newThread(r);
                backgroundThreadId = result.getId();
                return result;
            }
        };
        asyncQueueFullPolicy = AsyncQueueFullPolicyFactory.create();

        disruptor = new Disruptor<>(RingBufferLogEvent.FACTORY, ringBufferSize, threadFactory, ProducerType.MULTI,
                waitStrategy);

        final ExceptionHandler<RingBufferLogEvent> errorHandler = DisruptorUtil.getAsyncLoggerExceptionHandler();
        disruptor.setDefaultExceptionHandler(errorHandler);

        final RingBufferLogEventHandler[] handlers = {new RingBufferLogEventHandler()};
        disruptor.handleEventsWith(handlers);

        LOGGER.debug("[{}] Starting AsyncLogger disruptor for this context with ringbufferSize={}, waitStrategy={}, "
                + "exceptionHandler={}...", contextName, disruptor.getRingBuffer().getBufferSize(), waitStrategy
                .getClass().getSimpleName(), errorHandler);
        disruptor.start();

        LOGGER.trace("[{}] AsyncLoggers use a {} translator", contextName, useThreadLocalTranslator ? "threadlocal"
                : "vararg");
        super.start();
    }
