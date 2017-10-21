    protected Connection getConnection(String id) throws SQLException {

        Connection c = DriverManager.getConnection("jdbc:hsqldb:file:"
            + baseDir.getAbsolutePath() + '/' + id + "/dbfile", "SA", "");

        if (verbose) {
            System.err.println("Opening JDBC URL '" + "jdbc:hsqldb:file:"
                               + baseDir.getAbsolutePath() + '/' + id
                               + "/dbfile");
        }

        c.setAutoCommit(false);

        return c;
    }
