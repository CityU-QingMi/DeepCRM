    public synchronized byte[] getBytes(
            int parameterIndex) throws SQLException {

        Object x = getColumnInType(parameterIndex, Type.SQL_VARBINARY);

        if (x == null) {
            return null;
        }

        return ((BinaryData) x).getBytes();
    }
