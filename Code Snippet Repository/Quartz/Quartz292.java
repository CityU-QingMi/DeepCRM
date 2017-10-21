    @Override
    protected Connection getNonManagedTXConnection()
        throws JobPersistenceException {
        Connection conn = null;
        try {
            conn = DBConnectionManager.getInstance().getConnection(
                    getNonManagedTXDataSource());
        } catch (SQLException sqle) {
            throw new JobPersistenceException(
                "Failed to obtain DB connection from data source '"
                        + getNonManagedTXDataSource() + "': "
                        + sqle.toString(), sqle);
        } catch (Throwable e) {
            throw new JobPersistenceException(
                "Failed to obtain DB connection from data source '"
                        + getNonManagedTXDataSource() + "': "
                        + e.toString(), e);
        }

        if (conn == null) { 
            throw new JobPersistenceException(
                "Could not get connection from DataSource '"
                        + getNonManagedTXDataSource() + "'"); 
        }

        // Protect connection attributes we might change.
        conn = getAttributeRestoringConnection(conn);
        
        // Set any connection connection attributes we are to override.
        try {
            if (!isDontSetNonManagedTXConnectionAutoCommitFalse()) {
                conn.setAutoCommit(false);
            }
            
            if (isTxIsolationLevelReadCommitted()) {
                conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            }
        } catch (SQLException sqle) {
            getLog().warn("Failed to override connection auto commit/transaction isolation.", sqle);
        } catch (Throwable e) {
            try { conn.close(); } catch(Throwable tt) {}
            
            throw new JobPersistenceException(
                "Failure setting up connection.", e);
        }
        
        return conn;
    }
