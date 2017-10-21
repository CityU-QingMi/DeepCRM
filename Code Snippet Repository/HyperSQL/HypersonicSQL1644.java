    public synchronized void setLong(int parameterIndex,
                                     long x) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }
        checkSetParameterIndex(parameterIndex);

        if (parameterTypes[parameterIndex - 1].typeCode == Types.SQL_BIGINT) {
            parameterValues[--parameterIndex] = Long.valueOf(x);
            parameterSet[parameterIndex]      = Boolean.TRUE;

            return;
        }
        setLongParameter(parameterIndex, x);
    }
