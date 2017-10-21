    @Override
    public Thread newThread(final Runnable runnable) {
        // Log4jThread prefixes names with "Log4j2-".
        final Thread thread = new Log4jThread(group, runnable, threadNamePrefix + THREAD_NUMBER.getAndIncrement(), 0);
        if (thread.isDaemon() != daemon) {
            thread.setDaemon(daemon);
        }
        if (thread.getPriority() != priority) {
            thread.setPriority(priority);
        }
        return thread;
    }
