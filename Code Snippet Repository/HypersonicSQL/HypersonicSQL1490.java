    public synchronized Timestamp getTimestamp(
            int parameterIndex) throws SQLException {

        TimestampData t = (TimestampData) getColumnInType(parameterIndex,
            Type.SQL_TIMESTAMP);

        if (t == null) {
            return null;
        }

        return (Timestamp) Type.SQL_TIMESTAMP.convertSQLToJava(session, t);
    }
