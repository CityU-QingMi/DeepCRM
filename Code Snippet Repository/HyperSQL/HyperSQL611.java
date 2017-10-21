    public synchronized void setHoldability(
            int holdability) throws SQLException {

        checkClosed();

        switch (holdability) {

            case JDBCResultSet.HOLD_CURSORS_OVER_COMMIT :
            case JDBCResultSet.CLOSE_CURSORS_AT_COMMIT :
                break;
            default :
                throw JDBCUtil.invalidArgument();
        }
        rsHoldability = holdability;
    }
