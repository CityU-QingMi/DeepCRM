    public Object getArray() throws SQLException {

        checkClosed();

        Object[] array = new Object[data.length];

        for (int i = 0; i < data.length; i++) {
            array[i] = elementType.convertSQLToJava(sessionProxy, data[i]);
        }

        return array;
    }
