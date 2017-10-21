    public synchronized void setShort(int parameterIndex,
                                      short x) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }
        checkSetParameterIndex(parameterIndex);

        if (parameterTypes[parameterIndex - 1].typeCode
                == Types.SQL_SMALLINT) {
            parameterValues[--parameterIndex] = Integer.valueOf(x);
            parameterSet[parameterIndex]      = Boolean.TRUE;

            return;
        }
        setIntParameter(parameterIndex, x);
    }
