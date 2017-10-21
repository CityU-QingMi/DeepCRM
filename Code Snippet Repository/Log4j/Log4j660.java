    @Override
    public void append(final LogEvent event) {

        // Leverage the nice batching behaviour of async Loggers/Appenders:
        // we can signal the file manager that it needs to flush the buffer
        // to disk at the end of a batch.
        // From a user's point of view, this means that all log events are
        // _always_ available in the log file, without incurring the overhead
        // of immediateFlush=true.
        getManager().setEndOfBatch(event.isEndOfBatch()); // FIXME manager's EndOfBatch threadlocal can be deleted
        super.append(event); // TODO should only call force() if immediateFlush && endOfBatch?
    }
