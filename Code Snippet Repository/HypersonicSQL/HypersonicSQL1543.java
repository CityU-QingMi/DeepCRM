    public synchronized void setTransactionIsolation(
            int level) throws SQLException {

        checkClosed();

        switch (level) {

            case TRANSACTION_READ_UNCOMMITTED :
            case TRANSACTION_READ_COMMITTED :
            case TRANSACTION_REPEATABLE_READ :
            case TRANSACTION_SERIALIZABLE :
                break;
            default :
                throw JDBCUtil.invalidArgument();
        }

        try {
            sessionProxy.setIsolationDefault(level);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
