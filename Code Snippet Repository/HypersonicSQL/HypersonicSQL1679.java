    public void setFetchDirection(int direction) throws SQLException {

        checkClosed();

        switch (direction) {

            case ResultSet.FETCH_FORWARD : {
                break;
            }
            case ResultSet.FETCH_REVERSE : {
                checkNotForwardOnly();

                break;
            }
            case ResultSet.FETCH_UNKNOWN : {
                checkNotForwardOnly();

                break;
            }
            default : {
                throw JDBCUtil.notSupported();
            }
        }
    }
