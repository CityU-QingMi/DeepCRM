    @Override
    public final void log(final String finalMessage) {
        if (stopped) {
            return;
        }
        if (!queue.offer(finalMessage)) {
            PrintingAsyncQueueFullPolicy.ringbufferFull.incrementAndGet();
            try {
                queue.put(finalMessage);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
