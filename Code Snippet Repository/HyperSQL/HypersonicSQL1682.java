    public BigDecimal getBigDecimal(int columnIndex,
                                    int scale) throws SQLException {

        if (scale < 0) {
            throw JDBCUtil.outOfRangeArgument();
        }

        BigDecimal bd = getBigDecimal(columnIndex);

        if (bd != null) {
            bd = bd.setScale(scale, BigDecimal.ROUND_DOWN);
        }

        return bd;
    }
