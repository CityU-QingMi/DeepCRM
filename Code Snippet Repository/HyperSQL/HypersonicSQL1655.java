    public synchronized void clearParameters() throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }
        ArrayUtil.fillArray(parameterValues, null);
        ArrayUtil.fillArray(parameterSet, null);
        ArrayUtil.clearArray(ArrayUtil.CLASS_CODE_LONG, streamLengths, 0,
                             streamLengths.length);
    }
