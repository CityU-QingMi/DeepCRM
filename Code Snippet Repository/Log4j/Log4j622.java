    @Override
    public void append(final LogEvent event) {
        readLock.lock();
        try {
            final String str = getStringLayout().toSerializable(event);
            if (str.length() > 0) {
                manager.write(str);
                if (this.immediateFlush || event.isEndOfBatch()) {
                    manager.flush();
                }
            }
        } catch (final AppenderLoggingException ex) {
            error("Unable to write " + manager.getName() + " for appender " + getName() + ": " + ex);
            throw ex;
        } finally {
            readLock.unlock();
        }
    }
