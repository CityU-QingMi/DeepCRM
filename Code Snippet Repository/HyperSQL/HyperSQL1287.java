    public static void setTI(Connection c, String tiString)
            throws SQLException {
        int i = -1;
        if (tiString.equals("TRANSACTION_READ_UNCOMMITTED"))
            i = Connection.TRANSACTION_READ_UNCOMMITTED;
        if (tiString.equals("TRANSACTION_READ_COMMITTED"))
            i = Connection.TRANSACTION_READ_COMMITTED;
        if (tiString.equals("TRANSACTION_REPEATABLE_READ"))
            i = Connection.TRANSACTION_REPEATABLE_READ;
        if (tiString.equals("TRANSACTION_SERIALIZABLE"))
            i = Connection.TRANSACTION_SERIALIZABLE;
        if (tiString.equals("TRANSACTION_NONE"))
            i = Connection.TRANSACTION_NONE;
        if (i < 0) {
            throw new SQLException(
                    "Trans. isol. value not supported by "
                    + RCData.class.getName() + ": " + tiString);
        }
        c.setTransactionIsolation(i);
    }
