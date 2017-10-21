    public synchronized Time getTime(int parameterIndex) throws SQLException {

        TimeData t = (TimeData) getColumnInType(parameterIndex, Type.SQL_TIME);

        if (t == null) {
            return null;
        }

        return (Time) Type.SQL_TIME.convertSQLToJava(session, t);
    }
