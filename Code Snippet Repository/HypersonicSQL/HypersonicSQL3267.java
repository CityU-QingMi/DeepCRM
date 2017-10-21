    public void testMainAlreadyOpen()
    throws SQLException, IOException, TarMalformatException {

        try {
            setupConn("db1");

            try {
                DbBackupMain.main(new String[] {
                    "--save", baseDir.getAbsolutePath() + "/mainOpen.tar",
                    baseDir.getAbsolutePath() + "/db1/dbfile"
                });
            } catch (IllegalStateException ioe) {
                return;
            }
        } finally {
            shutdownAndCloseConn();
        }

        fail("Backup from main() did not throw even though DB is open");
    }
