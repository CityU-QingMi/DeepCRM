    public synchronized Date getDate(int parameterIndex) throws SQLException {

        TimestampData t = (TimestampData) getColumnInType(parameterIndex,
            Type.SQL_DATE);

        if (t == null) {
            return null;
        }

        return (Date) Type.SQL_DATE.convertSQLToJava(session, t);
    }
