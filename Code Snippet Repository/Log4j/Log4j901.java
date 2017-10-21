    @Override
    public void onEvent(final RingBufferLogEvent event, final long sequence,
            final boolean endOfBatch) throws Exception {
        event.execute(endOfBatch);
        event.clear();

        // notify the BatchEventProcessor that the sequence has progressed.
        // Without this callback the sequence would not be progressed
        // until the batch has completely finished.
        if (++counter > NOTIFY_PROGRESS_THRESHOLD) {
            sequenceCallback.set(sequence);
            counter = 0;
        }
    }
