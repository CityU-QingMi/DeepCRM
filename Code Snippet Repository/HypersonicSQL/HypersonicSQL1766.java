    public synchronized void setFetchDirection(
            int direction) throws SQLException {

        checkClosed();
        checkClosed();

        switch (direction) {

            case ResultSet.FETCH_FORWARD :
            case ResultSet.FETCH_REVERSE :
            case ResultSet.FETCH_UNKNOWN :
                fetchDirection = direction;

                break;
            default :
                throw JDBCUtil.invalidArgument();
        }
    }
