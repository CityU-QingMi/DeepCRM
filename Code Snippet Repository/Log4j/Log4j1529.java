    public static boolean shutdown(final ExecutorService executorService, final long timeout, final TimeUnit timeUnit, final String source) {
        if (executorService == null || executorService.isTerminated()) {
            return true;
        }
        executorService.shutdown(); // Disable new tasks from being submitted
        if (timeout > 0 && timeUnit == null) {
            throw new IllegalArgumentException(
                    String.format("%s can't shutdown %s when timeout = %,d and timeUnit = %s.", source, executorService,
                            timeout, timeUnit));
        }
        if (timeout > 0) {
            try {
                // Wait a while for existing tasks to terminate
                if (!executorService.awaitTermination(timeout, timeUnit)) {
                    executorService.shutdownNow(); // Cancel currently executing tasks
                    // Wait a while for tasks to respond to being cancelled
                    if (!executorService.awaitTermination(timeout, timeUnit)) {
                        LOGGER.error("{} pool {} did not terminate after {} {}", source, executorService, timeout,
                                timeUnit);
                    }
                    return false;
                }
            } catch (final InterruptedException ie) {
                // (Re-)Cancel if current thread also interrupted
                executorService.shutdownNow();
                // Preserve interrupt status
                Thread.currentThread().interrupt();
            }
        } else {
            executorService.shutdown();
        }
        return true;
    }
