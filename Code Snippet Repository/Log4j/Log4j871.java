    @Override
    public synchronized void start() {
        if (disruptor != null) {
            LOGGER.trace("AsyncLoggerConfigDisruptor not starting new disruptor for this configuration, "
                    + "using existing object.");
            return;
        }
        LOGGER.trace("AsyncLoggerConfigDisruptor creating new disruptor for this configuration.");
        ringBufferSize = DisruptorUtil.calculateRingBufferSize("AsyncLoggerConfig.RingBufferSize");
        final WaitStrategy waitStrategy = DisruptorUtil.createWaitStrategy("AsyncLoggerConfig.WaitStrategy");

        final ThreadFactory threadFactory = new Log4jThreadFactory("AsyncLoggerConfig-", true, Thread.NORM_PRIORITY) {
            @Override
            public Thread newThread(final Runnable r) {
                final Thread result = super.newThread(r);
                backgroundThreadId = result.getId();
                return result;
            }
        };
        asyncQueueFullPolicy = AsyncQueueFullPolicyFactory.create();

        translator = mutable ? MUTABLE_TRANSLATOR : TRANSLATOR;
        factory = mutable ? MUTABLE_FACTORY : FACTORY;
        disruptor = new Disruptor<>(factory, ringBufferSize, threadFactory, ProducerType.MULTI, waitStrategy);

        final ExceptionHandler<Log4jEventWrapper> errorHandler = DisruptorUtil.getAsyncLoggerConfigExceptionHandler();
        disruptor.setDefaultExceptionHandler(errorHandler);

        final Log4jEventWrapperHandler[] handlers = {new Log4jEventWrapperHandler()};
        disruptor.handleEventsWith(handlers);

        LOGGER.debug("Starting AsyncLoggerConfig disruptor for this configuration with ringbufferSize={}, "
                + "waitStrategy={}, exceptionHandler={}...", disruptor.getRingBuffer().getBufferSize(), waitStrategy
                .getClass().getSimpleName(), errorHandler);
        disruptor.start();
        super.start();
    }
