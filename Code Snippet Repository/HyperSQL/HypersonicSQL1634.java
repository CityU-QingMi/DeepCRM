    public synchronized void setInt(int parameterIndex,
                                    int x) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }
        checkSetParameterIndex(parameterIndex);

        if (parameterTypes[parameterIndex - 1].typeCode == Types.SQL_INTEGER) {
            parameterValues[--parameterIndex] = Integer.valueOf(x);
            parameterSet[parameterIndex]      = Boolean.TRUE;

            return;
        }
        setIntParameter(parameterIndex, x);
    }
