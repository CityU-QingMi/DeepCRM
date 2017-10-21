    public Date getDate(int columnIndex) throws SQLException {

        Object t = getColumnInType(columnIndex, Type.SQL_DATE);

        if (t == null) {
            return null;
        }

        return (Date) Type.SQL_DATE.convertSQLToJava(session, t);
    }
