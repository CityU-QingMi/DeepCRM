    @Override
    protected void writeInternal(final LogEvent event) {
        if (!this.isRunning() || this.entityManagerFactory == null || this.entityManager == null
                || this.transaction == null) {
            throw new AppenderLoggingException(
                    "Cannot write logging event; JPA manager not connected to the database.");
        }

        AbstractLogEventWrapperEntity entity;
        try {
            entity = this.entityConstructor.newInstance(event);
        } catch (final Exception e) {
            throw new AppenderLoggingException("Failed to instantiate entity class [" + this.entityClassName + "].", e);
        }

        try {
            this.entityManager.persist(entity);
        } catch (final Exception e) {
            if (this.transaction != null && this.transaction.isActive()) {
                this.transaction.rollback();
                this.transaction = null;
            }
            throw new AppenderLoggingException("Failed to insert record for log event in JPA manager: " +
                    e.getMessage(), e);
        }
    }
