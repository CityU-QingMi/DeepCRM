    @Override
    public final void append(final LogEvent event) {
        this.readLock.lock();
        try {
            this.getManager().write(event);
        } catch (final LoggingException e) {
            LOGGER.error("Unable to write to database [{}] for appender [{}].", this.getManager().getName(),
                    this.getName(), e);
            throw e;
        } catch (final Exception e) {
            LOGGER.error("Unable to write to database [{}] for appender [{}].", this.getManager().getName(),
                    this.getName(), e);
            throw new AppenderLoggingException("Unable to write to database in appender: " + e.getMessage(), e);
        } finally {
            this.readLock.unlock();
        }
    }
