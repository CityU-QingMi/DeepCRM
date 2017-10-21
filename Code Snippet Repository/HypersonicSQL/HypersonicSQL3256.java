    protected void shutdownAndCloseConn() throws SQLException {

        if (conn == null) {
            return;
        }

        if (!alreadyShut) {
            conn.createStatement().executeUpdate("SHUTDOWN");

            alreadyShut = true;
        }

        if (verbose) {
            System.err.println("Shut down 'db1'");
        }

        conn.close();

        conn = null;
    }
