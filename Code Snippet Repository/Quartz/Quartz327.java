    protected Connection getConnection() throws JobPersistenceException {
        Connection conn;
        try {
            conn = DBConnectionManager.getInstance().getConnection(
                    getDataSource());
        } catch (SQLException sqle) {
            throw new JobPersistenceException(
                    "Failed to obtain DB connection from data source '"
                    + getDataSource() + "': " + sqle.toString(), sqle);
        } catch (Throwable e) {
            throw new JobPersistenceException(
                    "Failed to obtain DB connection from data source '"
                    + getDataSource() + "': " + e.toString(), e);
        }

        if (conn == null) { 
            throw new JobPersistenceException(
                "Could not get connection from DataSource '"
                + getDataSource() + "'"); 
        }

        // Protect connection attributes we might change.
        conn = getAttributeRestoringConnection(conn);

        // Set any connection connection attributes we are to override.
        try {
            if (!isDontSetAutoCommitFalse()) {
                conn.setAutoCommit(false);
            }

            if(isTxIsolationLevelSerializable()) {
                conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            }
        } catch (SQLException sqle) {
            getLog().warn("Failed to override connection auto commit/transaction isolation.", sqle);
        } catch (Throwable e) {
            try { conn.close(); } catch(Throwable ignored) {}
            
            throw new JobPersistenceException(
                "Failure setting up connection.", e);
        }
    
        return conn;
    }
