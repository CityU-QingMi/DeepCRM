    public synchronized long position(Clob searchstr,
                                      long start) throws SQLException {

        checkClosed();

        if (!isInLimits(Long.MAX_VALUE, start - 1, 0)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        if (searchstr instanceof JDBCClobClient) {
            ClobDataID searchClob = ((JDBCClobClient) searchstr).clob;

            try {
                return clob.position(session, searchClob, start - 1);
            } catch (HsqlException e) {
                throw JDBCUtil.sqlException(e);
            }
        }

        return position(searchstr.getSubString(1, (int) searchstr.length()),
                        start);
    }
