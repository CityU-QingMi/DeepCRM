    JDBCConnection getInternalConnection() {

        if (intConnection == null) {
            intConnection = new JDBCConnection(this);
        }

        JDBCDriver.driverInstance.threadConnection.set(intConnection);

        return intConnection;
    }
