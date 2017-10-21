    @Override
    protected boolean releaseSub(final long timeout, final TimeUnit timeUnit) {
        if (reconnector != null) {
            reconnector.shutdown();
            reconnector.interrupt();
            reconnector = null;
        }
        boolean closed = false;
        closed &= closeJndiManager();
        closed &= closeMessageProducer();
        closed &= closeSession();
        closed &= closeConnection();
        return closed && this.jndiManager.stop(timeout, timeUnit);
    }
