    public synchronized BigDecimal getBigDecimal(int parameterIndex,
            int scale) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (scale < 0) {
            throw JDBCUtil.outOfRangeArgument();
        }

        BigDecimal bd = getBigDecimal(parameterIndex);

        if (bd != null) {
            bd = bd.setScale(scale, BigDecimal.ROUND_DOWN);
        }

        return bd;
    }
