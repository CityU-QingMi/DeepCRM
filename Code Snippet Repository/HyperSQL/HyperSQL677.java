    public int getDefaultTransactionIsolation() throws SQLException {

        ResultSet rs = execute("CALL DATABASE_ISOLATION_LEVEL()");

        rs.next();

        String result = rs.getString(1);

        rs.close();

        if (result.startsWith("READ COMMITTED")) {
            return Connection.TRANSACTION_READ_COMMITTED;
        }

        if (result.startsWith("READ UNCOMMITTED")) {
            return Connection.TRANSACTION_READ_UNCOMMITTED;
        }

        if (result.startsWith("SERIALIZABLE")) {
            return Connection.TRANSACTION_SERIALIZABLE;
        }

        return Connection.TRANSACTION_READ_COMMITTED;
    }
