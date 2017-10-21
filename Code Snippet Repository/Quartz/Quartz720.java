        @Override
        public Connection getConnection() throws SQLException {
            if (Thread.currentThread() == safeThread) {
                return DBConnectionManager.getInstance().getConnection(delegateName);
            } else {
                createFailure();
                return (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[] {Connection.class},
                        new FlakyConnectionInvocationHandler(DBConnectionManager.getInstance().getConnection(delegateName)));
            }
        }
