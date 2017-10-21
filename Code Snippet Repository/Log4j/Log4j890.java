    public static long getExecutorThreadId(final ExecutorService executor) {
        final Future<Long> result = executor.submit(new Callable<Long>() {
            @Override
            public Long call() {
                return Thread.currentThread().getId();
            }
        });
        try {
            return result.get();
        } catch (final Exception ex) {
            final String msg = "Could not obtain executor thread Id. "
                    + "Giving up to avoid the risk of application deadlock.";
            throw new IllegalStateException(msg, ex);
        }
    }
