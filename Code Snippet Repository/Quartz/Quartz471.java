    @Override
    protected void executeSQL(Connection conn, final String lockName, final String expandedSQL, final String expandedInsertSQL) throws LockException {
        SQLException lastFailure = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            try {
                if (!lockViaUpdate(conn, lockName, expandedSQL)) {
                    lockViaInsert(conn, lockName, expandedInsertSQL);
                }
                return;
            } catch (SQLException e) {
                lastFailure = e;
                if ((i + 1) == RETRY_COUNT) {
                    getLog().debug("Lock '{}' was not obtained by: {}", lockName, Thread.currentThread().getName());
                } else {
                    getLog().debug("Lock '{}' was not obtained by: {} - will try again.", lockName, Thread.currentThread().getName());
                }
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException _) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new LockException("Failure obtaining db row lock: " + lastFailure.getMessage(), lastFailure);
    }
