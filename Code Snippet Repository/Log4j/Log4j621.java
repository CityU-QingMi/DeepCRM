    @Override
    protected boolean stop(final long timeout, final TimeUnit timeUnit, final boolean changeLifeCycleState) {
        boolean stopped = super.stop(timeout, timeUnit, changeLifeCycleState);
        stopped &= manager.stop(timeout, timeUnit);
        if (changeLifeCycleState) {
            setStopped();
        }
        LOGGER.debug("Appender {} stopped with status {}", getName(), stopped);
        return stopped;
    }
