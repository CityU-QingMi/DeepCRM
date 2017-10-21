    @Override
    protected boolean commitAndClose() {
        boolean closed = true;
        try {
            if (this.transaction != null && this.transaction.isActive()) {
                this.transaction.commit();
            }
        } catch (final Exception e) {
            if (this.transaction != null && this.transaction.isActive()) {
                this.transaction.rollback();
            }
        } finally {
            this.transaction = null;
            try {
                if (this.entityManager != null && this.entityManager.isOpen()) {
                    this.entityManager.close();
                }
            } catch (final Exception e) {
                logWarn("Failed to close entity manager while logging event or flushing buffer", e);
                closed = false;
            } finally {
                this.entityManager = null;
            }
        }
        return closed;
    }
