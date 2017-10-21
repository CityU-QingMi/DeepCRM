    @Override
    protected boolean releaseSub(final long timeout, final TimeUnit timeUnit) {
    	boolean closed = true;
        if (rpcClient != null) {
            try {
                synchronized(this) {
                    try {
                        if (batchSize > 1 && batchEvent.getEvents().size() > 0) {
                            send(batchEvent);
                        }
                    } catch (final Exception ex) {
                        LOGGER.error("Error sending final batch: {}", ex.getMessage());
                        closed = false;
                    }
                }
                rpcClient.close();
            } catch (final Exception ex) {
                LOGGER.error("Attempt to close RPC client failed", ex);
                closed = false;
            }
        }
        rpcClient = null;
        return closed;
    }
