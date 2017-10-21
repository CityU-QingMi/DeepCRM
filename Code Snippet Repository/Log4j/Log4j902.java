    @Override
    public void translateTo(final RingBufferLogEvent event, final long sequence) {

        event.setValues(asyncLogger, loggerName, marker, fqcn, level, message, thrown,
                // config properties are taken care of in the EventHandler thread
                // in the AsyncLogger#actualAsyncLog method
                injector.injectContextData(null, (StringMap) event.getContextData()), contextStack,
                threadId, threadName, threadPriority, location, currentTimeMillis, nanoTime);

        clear(); // clear the translator
    }
