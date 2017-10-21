    @Override
    protected void connectAndStart() {
        try {
            this.entityManager = this.entityManagerFactory.createEntityManager();
            this.transaction = this.entityManager.getTransaction();
            this.transaction.begin();
        } catch (final Exception e) {
            throw new AppenderLoggingException(
                    "Cannot write logging event or flush buffer; manager cannot create EntityManager or transaction.", e
            );
        }
    }
