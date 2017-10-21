    @SuppressWarnings("")
    public <T>T unwrap(java.lang.Class<T> iface) throws java.sql.SQLException {

        checkClosed();

        if (isWrapperFor(iface)) {
            return (T) this;
        }

        throw JDBCUtil.invalidArgument("iface: " + iface);
    }
