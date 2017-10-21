    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        setStopping();
        if (isExecutorServiceSet()) {
            LOGGER.debug("{} shutting down threads in {}", name, getExecutorService());
            executorService.shutdown();
            try {
                executorService.awaitTermination(timeout, timeUnit);
            } catch (final InterruptedException ie) {
                executorService.shutdownNow();
                try {
                    executorService.awaitTermination(timeout, timeUnit);
                } catch (final InterruptedException inner) {
                    LOGGER.warn("{} stopped but some scheduled services may not have completed.", name);
                }
                // Preserve interrupt status
                Thread.currentThread().interrupt();
            }
        }
        setStopped();
        return true;
    }
