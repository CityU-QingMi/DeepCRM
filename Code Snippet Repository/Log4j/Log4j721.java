    @Override
    protected boolean shutdownInternal() {
        boolean closed = true;
        if (this.entityManager != null || this.transaction != null) {
            closed &= this.commitAndClose();
        }
        if (this.entityManagerFactory != null && this.entityManagerFactory.isOpen()) {
            this.entityManagerFactory.close();
        }
        return closed;
    }
