    public Timestamp getTimestamp(int columnIndex) throws SQLException {

        Object t = getColumnInType(columnIndex, Type.SQL_TIMESTAMP);

        if (t == null) {
            return null;
        }

        return (Timestamp) Type.SQL_TIMESTAMP.convertSQLToJava(session, t);
    }
