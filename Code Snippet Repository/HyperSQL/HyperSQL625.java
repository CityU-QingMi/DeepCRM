    public void setClientInfo(
            Properties properties) throws SQLClientInfoException {

        if (!this.isClosed && (properties == null || properties.isEmpty())) {
            return;
        }

        SQLClientInfoException ex = new SQLClientInfoException();

        if (this.isClosed) {
            ex.initCause(JDBCUtil.connectionClosedException());
        } else {
            ex.initCause(JDBCUtil.notSupported());
        }

        throw ex;
    }
