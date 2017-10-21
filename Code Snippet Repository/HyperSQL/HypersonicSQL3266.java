    public void mainBackupAndRestore(String baseTarName)
    throws SQLException, IOException, TarMalformatException {

        DbBackupMain.main(new String[] {
            "--save", baseDir.getAbsolutePath() + '/' + baseTarName,
            baseDir.getAbsolutePath() + "/db1/dbfile"
        });

        File destDir = new File(baseDir, "mainrestored");

        if (!destDir.mkdir()) {
            throw new IOException("Failed to make new dir. to restore to: "
                                  + destDir.getAbsolutePath());
        }

        DbBackupMain.main(new String[] {
            "--extract", baseDir.getAbsolutePath() + '/' + baseTarName,
            destDir.getAbsolutePath()
        });

        try {
            setupConn("mainrestored");

            ResultSet rs =
                conn.createStatement().executeQuery("SELECT * FROM t;");

            rs.next();
            assertEquals("Wrong table 't' contents", 34, rs.getInt("i"));
        } finally {
            shutdownAndCloseConn();
        }
    }
