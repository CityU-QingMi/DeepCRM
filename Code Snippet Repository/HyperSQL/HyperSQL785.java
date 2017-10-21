    public Time getTime(int columnIndex) throws SQLException {

        Object t = getColumnInType(columnIndex, Type.SQL_TIME);

        if (t == null) {
            return null;
        }

        return (Time) Type.SQL_TIME.convertSQLToJava(session, t);
    }
